import { Component } from '@angular/core';
import { DataService } from "../data/service/data.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

    loadedData: string | undefined;

    constructor(private dataService: DataService) { }

    downloadFile() {
        this.dataService.downloadAll().subscribe({
            next: (data) => {
                const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
                saveAs(blob, 'data.json');
            }
        });
    }

    onFileSelected(event: Event) {
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

    submitJson() {
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
