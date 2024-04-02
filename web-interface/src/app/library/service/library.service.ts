import { Injectable } from '@angular/core';
import {Libraries} from "../model/libraries";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Library} from "../model/library";

@Injectable()
export class LibraryService {
    constructor(private httpClient: HttpClient) { }

    getLibraries(): Observable<Libraries> {
        return this.httpClient.get<Libraries>('/api/libraries')
    }

    getLibrary(uuid: string): Observable<Library> {
        return this.httpClient.get<Library>('/api/libraries/' + uuid)
    }
}
