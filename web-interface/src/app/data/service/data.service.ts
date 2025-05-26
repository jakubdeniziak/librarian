import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Endpoints} from "../../endpoints";

@Injectable({
  providedIn: 'root'
})
export class DataService {

    constructor(private http: HttpClient) {}

    importAll(data: string): Observable<any> {
        return this.http.put(Endpoints.DATA_ALL, data);
    }

    downloadAll(): Observable<any> {
        return this.http.get(Endpoints.DATA_ALL, { responseType: 'json' });
    }

}
