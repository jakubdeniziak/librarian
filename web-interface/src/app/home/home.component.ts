import { Component } from '@angular/core';
import { DataService } from "../data/service/data.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

    constructor(private dataService: DataService) { }

    downloadFile() {
        this.dataService.fetchData().subscribe({
            next: (data) => {
                const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
                saveAs(blob, 'data.json');
            }
        });
    }

}
