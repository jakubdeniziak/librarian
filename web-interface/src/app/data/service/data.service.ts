import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {

    dataEndpoint = '/api/data/all'

    constructor(private http: HttpClient) {}

    importAll(data: string): Observable<any> {
        return this.http.put(this.dataEndpoint, data);
    }

    downloadAll(): Observable<any> {
        return this.http.get(this.dataEndpoint, { responseType: 'json' });
    }

}
