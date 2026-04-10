import {Injectable} from '@angular/core';
import {Libraries} from "@features/library/models/libraries";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LibraryDetails} from "@features/library/models/library.details";
import {LibraryForm} from "@features/library/models/library.form";
import {Endpoints} from "@app/endpoints";

@Injectable({
  providedIn: 'root'
})
export class LibraryService {
  constructor(private http: HttpClient) {
  }

  public putLibrary(uuid: string, request: LibraryForm): Observable<any> {
    return this.http.put<void>(`${Endpoints.LIBRARIES}/${uuid}`, request);
  }

  public getLibrary(uuid: string): Observable<LibraryDetails> {
    return this.http.get<LibraryDetails>(`${Endpoints.LIBRARIES}/${uuid}`);
  }

  public getLibraries(): Observable<Libraries> {
    return this.http.get<Libraries>(Endpoints.LIBRARIES);
  }

  public getLibrariesCount(): Observable<number> {
    return this.http.get<number>(`${Endpoints.LIBRARIES}/count`);
  }

  public deleteLibrary(uuid: string): Observable<any> {
    return this.http.delete(`${Endpoints.LIBRARIES}/${uuid}`);
  }
}
