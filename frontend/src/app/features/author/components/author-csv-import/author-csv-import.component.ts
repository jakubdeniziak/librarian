import {Component, EventEmitter, HostListener, Input, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import Papa from 'papaparse';
import {v4 as uuid} from 'uuid';
import {from, mergeMap, of, Subject, toArray} from 'rxjs';
import {catchError, debounceTime, distinctUntilChanged, finalize, map} from 'rxjs/operators';
import {AuthorService} from '@features/author/services/author.service';
import {AuthorForm} from '@features/author/models/author-form.model';

type ColumnKey = string;

type TargetField =
  | 'firstName'
  | 'lastName'
  | 'description'
  | 'pictureUrl'
  | 'aliases'
  | 'genres'
  | 'birthDate'
  | 'deathDate'
  | '__ignore__';

interface ParsedRow {
  index: number;
  raw: Record<string, string>;
  form?: AuthorForm;
  errors: string[];
  warnings: string[];
  duplicateInFile?: boolean;
}

interface ImportResult {
  created: number;
  failed: number;
  skipped: number;
}

@Component({
  selector: 'app-author-csv-import',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './author-csv-import.component.html',
  styleUrl: './author-csv-import.component.css'
})
export class AuthorCsvImportComponent {
  @Input() open = false;
  @Output() closed = new EventEmitter<void>();
  @Output() completed = new EventEmitter<void>();

  protected step: 1 | 2 | 3 = 1;

  protected fileName: string | undefined;
  protected hasHeader = true;

  protected rawRows: Array<Record<string, string>> = [];
  protected columns: ColumnKey[] = [];

  protected mapping: Partial<Record<TargetField, ColumnKey>> = {};

  protected rows: ParsedRow[] = [];

  protected isParsing = false;
  protected parseError: string | undefined;

  protected isImporting = false;
  protected importError: string | undefined;
  protected importResult: ImportResult | undefined;
  protected progressDone = 0;
  protected progressTotal = 0;

  private recomputeRequested$ = new Subject<string>();

  constructor(private authorService: AuthorService) {
    this.recomputeRequested$.pipe(
      debounceTime(150),
      distinctUntilChanged(),
    ).subscribe(() => this.recomputeRows());
  }

  protected close(): void {
    this.reset();
    this.closed.emit();
  }

  @HostListener('document:keydown.escape', ['$event'])
  protected onEsc(event: KeyboardEvent): void {
    if (!this.open) return;
    event.preventDefault();
    event.stopPropagation();
    this.close();
  }

  protected onOverlayClick(event: MouseEvent): void {
    if ((event.target as HTMLElement)?.id === 'author-csv-import-overlay') {
      this.close();
    }
  }

  protected async onFileSelected(event: Event): Promise<void> {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];
    if (!file) return;

    this.reset(false);
    this.fileName = file.name;
    this.isParsing = true;
    this.parseError = undefined;

    Papa.parse<Record<string, string> | string[]>(file, {
      header: this.hasHeader,
      skipEmptyLines: true,
      transformHeader: header => header.trim(),
      worker: false,
      complete: (results) => {
        this.isParsing = false;

        if (results.errors?.length) {
          this.parseError = results.errors[0].message;
          return;
        }

        const data = results.data as any[];
        if (!data || data.length === 0) {
          this.parseError = 'No rows found in CSV.';
          return;
        }

        if (this.hasHeader) {
          this.rawRows = data.map(r => this.cleanRecord(r as Record<string, unknown>));
          this.columns = this.rawRows.length ? Object.keys(this.rawRows[0]) : [];
        } else {
          const maxLen = Math.max(...data.map(r => (r as string[]).length));
          this.columns = Array.from({length: maxLen}, (_, i) => `Column ${i + 1}`);
          this.rawRows = data.map((arr: string[]) => {
            const rec: Record<string, string> = {};
            this.columns.forEach((c, idx) => rec[c] = (arr[idx] ?? '').toString());
            return this.cleanRecord(rec);
          });
        }

        this.autoMapColumns();
        this.step = 2;
        this.requestRecompute();
      },
      error: () => {
        this.isParsing = false;
        this.parseError = 'Failed to read CSV.';
      }
    });
  }

  protected goToStep(step: 1 | 2 | 3): void {
    if (step === 3) {
      this.recomputeRows();
    }
    this.step = step;
  }

  protected requestRecompute(): void {
    this.recomputeRequested$.next(JSON.stringify(this.mapping));
  }


  protected recomputeRows(): void {
    this.importResult = undefined;
    this.importError = undefined;

    const mappedRows: ParsedRow[] = this.rawRows.map((raw, idx) => {
      const errors: string[] = [];
      const warnings: string[] = [];

      const firstName = this.valueFrom(raw, this.mapping.firstName);
      const lastName = this.valueFrom(raw, this.mapping.lastName);
      const description = this.valueFrom(raw, this.mapping.description);
      const pictureUrl = this.valueFrom(raw, this.mapping.pictureUrl);
      const aliasesRaw = this.valueFrom(raw, this.mapping.aliases);
      const genresRaw = this.valueFrom(raw, this.mapping.genres);
      const birthDateRaw = this.valueFrom(raw, this.mapping.birthDate);
      const deathDateRaw = this.valueFrom(raw, this.mapping.deathDate);

      const aliases = this.splitList(aliasesRaw);
      const genres = this.splitList(genresRaw);

      const birthDate = this.normalizeDate(birthDateRaw);
      const deathDate = this.normalizeDate(deathDateRaw);

      if (birthDateRaw && !birthDate) warnings.push('Birth date has invalid format (expected YYYY-MM-DD)');
      if (deathDateRaw && !deathDate) warnings.push('Death date has invalid format (expected YYYY-MM-DD)');

      if (!firstName) errors.push('Missing first name');
      if (!lastName) errors.push('Missing last name');

      const form: AuthorForm | undefined = (firstName && lastName)
        ? {
          firstName,
          lastName,
          description: description ?? '',
          ...(pictureUrl ? {pictureUrl} : {}),
          ...(aliases?.length ? {aliases} : {}),
          ...(genres?.length ? {genres} : {}),
          ...(birthDate ? {birthDate} : {}),
          ...(deathDate ? {deathDate} : {}),
        }
        : undefined;

      return {
        index: idx + 1,
        raw,
        form,
        errors,
        warnings,
      };
    });

    // dedupe within file
    const seen = new Set<string>();
    for (const row of mappedRows) {
      if (!row.form) continue;
      const key = this.normalizeKey(row.form.firstName, row.form.lastName);
      if (seen.has(key)) {
        row.duplicateInFile = true;
        row.warnings.push('Duplicate in CSV');
      } else {
        seen.add(key);
      }
    }

    this.rows = mappedRows;
  }

  protected startImport(): void {
    if (this.isImporting) return;

    const candidates = this.rows
      .filter(r => !!r.form)
      .filter(r => r.errors.length === 0)
      .filter(r => !r.duplicateInFile);

    this.progressDone = 0;
    this.progressTotal = candidates.length;
    this.importResult = undefined;
    this.importError = undefined;
    this.isImporting = true;

    if (candidates.length === 0) {
      this.importResult = {created: 0, failed: 0, skipped: 0};
      this.isImporting = false;
      return;
    }

    const concurrency = 4;

    from(candidates).pipe(
      mergeMap((row) => {
        const id = uuid();
        return this.authorService.putAuthor(id, row.form!).pipe(
          map(() => ({ok: true, row} as const)),
          catchError((err) => of({ok: false, row, err} as const)),
          finalize(() => this.progressDone++)
        );
      }, concurrency),
      toArray(),
      map(results => {
        const created = results.filter(r => r.ok).length;
        const failed = results.filter(r => !r.ok).length;
        const skipped = this.rows.filter(r => !r.form || r.errors.length > 0 || r.duplicateInFile).length;
        return {created, failed, skipped} satisfies ImportResult;
      }),
      finalize(() => this.isImporting = false)
    ).subscribe({
      next: res => {
        this.importResult = res;
        if (res.failed === 0) {
          this.completed.emit();
        }
      },
      error: () => {
        this.importError = 'Import failed unexpectedly.';
      }
    });
  }

  protected get validCount(): number {
    return this.rows.filter(r => r.form && r.errors.length === 0 && !r.duplicateInFile).length;
  }

  protected get invalidCount(): number {
    return this.rows.filter(r => r.errors.length > 0).length;
  }

  protected get duplicateCount(): number {
    return this.rows.filter(r => r.duplicateInFile).length;
  }

  private autoMapColumns(): void {
    const find = (candidates: string[]) => {
      const lowered = this.columns.map(c => ({c, l: c.toLowerCase()}));
      for (const cand of candidates) {
        const found = lowered.find(x => x.l === cand);
        if (found) return found.c;
      }
      return undefined;
    };

    this.mapping.firstName = this.mapping.firstName ?? find(['firstname', 'first_name', 'first name', 'givenname', 'given_name']);
    this.mapping.lastName = this.mapping.lastName ?? find(['lastname', 'last_name', 'last name', 'surname', 'familyname', 'family_name']);
    this.mapping.description = this.mapping.description ?? find(['description', 'bio', 'biography', 'about']);
    this.mapping.pictureUrl = this.mapping.pictureUrl ?? find(['pictureurl', 'picture_url', 'image', 'imageurl', 'image_url', 'photo', 'photourl', 'photo_url', 'avatar', 'avatarurl', 'avatar_url']);
    this.mapping.aliases = this.mapping.aliases ?? find(['aliases', 'alias', 'aka', 'also_known_as']);
    this.mapping.genres = this.mapping.genres ?? find(['genres', 'genre', 'tags']);
    this.mapping.birthDate = this.mapping.birthDate ?? find(['birthdate', 'birth_date', 'dob', 'dateofbirth', 'date_of_birth']);
    this.mapping.deathDate = this.mapping.deathDate ?? find(['deathdate', 'death_date', 'dod', 'dateofdeath', 'date_of_death']);
  }

  private valueFrom(raw: Record<string, string>, column?: ColumnKey): string | undefined {
    if (!column) return undefined;
    const val = raw[column];
    if (val === undefined || val === null) return undefined;
    const trimmed = `${val}`.trim();
    return trimmed.length ? trimmed : undefined;
  }

  private cleanRecord(rec: Record<string, unknown>): Record<string, string> {
    const out: Record<string, string> = {};
    Object.entries(rec).forEach(([k, v]) => {
      out[String(k).trim()] = (v ?? '').toString();
    });
    return out;
  }

  private normalizeKey(firstName?: string, lastName?: string): string {
    return `${(firstName ?? '').trim().toLowerCase()}|${(lastName ?? '').trim().toLowerCase()}`;
  }

  private splitList(raw?: string): string[] | undefined {
    if (!raw) return undefined;
    const parts = raw
      .split(/[;,]/g)
      .map(x => x.trim())
      .filter(Boolean);
    return parts.length ? parts : undefined;
  }

  private normalizeDate(raw?: string): string | undefined {
    if (!raw) return undefined;
    const trimmed = raw.trim();
    if (/^\d{4}-\d{2}-\d{2}$/.test(trimmed)) return trimmed;
    return undefined;
  }


  protected reset(keepOpen = true): void {
    this.step = 1;
    this.fileName = undefined;
    this.rawRows = [];
    this.columns = [];
    this.mapping = {};
    this.rows = [];
    this.isParsing = false;
    this.parseError = undefined;
    this.isImporting = false;
    this.importError = undefined;
    this.importResult = undefined;
    this.progressDone = 0;
    this.progressTotal = 0;

    if (!keepOpen) {
    }
  }
}
