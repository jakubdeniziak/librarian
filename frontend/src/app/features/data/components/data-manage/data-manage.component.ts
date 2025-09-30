import {Component} from '@angular/core';
import {saveAs} from "file-saver";
import {DataService} from "@features/data/services/data.service";
import {PageHeaderComponent} from "@shared/components/page-header/page-header.component";

@Component({
  selector: 'app-data',
  templateUrl: './data-manage.component.html',
  imports: [
    PageHeaderComponent
  ],
  styleUrl: './data-manage.component.css'
})
export class DataComponent {
  protected loadedData: string | undefined;

  constructor(private dataService: DataService) {
  }

  protected downloadFile() {
    this.dataService.downloadAll().subscribe({
      next: (data) => {
        const blob = new Blob([JSON.stringify(data, null, 2)], {type: 'application/json'});
        saveAs(blob, 'data.json');
      }
    });
  }

  protected onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        try {
          this.loadedData = JSON.parse(e.target?.result as string);
        } catch (error) {
          alert('Invalid JSON file!');
        }
      };
      reader.readAsText(file);
    }
  }

  protected submitJson() {
    if (this.loadedData) {
      this.dataService.importAll(this.loadedData).subscribe({
        next: () => {
          alert('Data submitted successfully!');
        },
        error: (error) => {
          console.error('Error submitting data:', error);
          alert('Failed to submit data!');
        }
      });
    }
  }
}
