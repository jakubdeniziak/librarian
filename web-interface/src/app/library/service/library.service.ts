import { Injectable } from '@angular/core';
import {Libraries} from "../model/libraries";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LibraryDetails} from "../model/library-details";
import {LibraryForm} from "../model/library-form";
import {LibraryBookForm} from "../model/library-book/library-book-form";

@Injectable()
export class LibraryService {
    constructor(private httpClient: HttpClient) { }

    getLibraries(): Observable<Libraries> {
        return this.httpClient.get<Libraries>('/api/libraries')
    }

    getLibrary(uuid: string): Observable<LibraryDetails> {
        return this.httpClient.get<LibraryDetails>('/api/libraries/' + uuid)
    }

    putLibrary(uuid: string, request: LibraryForm): Observable<any> {
        return this.httpClient.put('/api/libraries/' + uuid, request);
    }

    deleteLibrary(uuid: string): Observable<any> {
        return this.httpClient.delete('/api/libraries/' + uuid);
    }

    addBookToLibrary(libraryId: string, bookId: string, request: LibraryBookForm): Observable<any> {
        return this.httpClient.put('/api/libraries/' + libraryId + '/books/' + bookId, request);
    }
}
