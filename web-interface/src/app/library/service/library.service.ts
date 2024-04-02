import { Injectable } from '@angular/core';
import {Libraries} from "../model/libraries";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LibraryDetails} from "../model/library-details";

@Injectable()
export class LibraryService {
    constructor(private httpClient: HttpClient) { }

    getLibraries(): Observable<Libraries> {
        return this.httpClient.get<Libraries>('/api/libraries')
    }

    getLibrary(uuid: string): Observable<LibraryDetails> {
        return this.httpClient.get<LibraryDetails>('/api/libraries/' + uuid)
    }
}
