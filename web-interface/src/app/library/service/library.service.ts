import { Injectable } from '@angular/core';
import {Libraries} from "../model/libraries";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LibraryDetails} from "../model/library-details";
import {LibraryForm} from "../model/library-form";
import {LibraryBookForm} from "../model/library-book/library-book-form";
import {Endpoints} from "../../endpoints";

@Injectable()
export class LibraryService {

    constructor(private httpClient: HttpClient) { }

    getLibraries(): Observable<Libraries> {
        return this.httpClient.get<Libraries>(Endpoints.LIBRARIES)
    }

    getLibrary(uuid: string): Observable<LibraryDetails> {
        return this.httpClient.get<LibraryDetails>(Endpoints.LIBRARIES + '/' + uuid)
    }

    putLibrary(uuid: string, request: LibraryForm): Observable<any> {
        return this.httpClient.put(Endpoints.LIBRARIES + '/' + uuid, request);
    }

    deleteLibrary(uuid: string): Observable<any> {
        return this.httpClient.delete(Endpoints.LIBRARIES + '/' + uuid);
    }

    addBookToLibrary(libraryId: string, bookId: string, request: LibraryBookForm): Observable<any> {
        return this.httpClient.put(Endpoints.LIBRARIES + '/' + libraryId + '/books/' + bookId, request);
    }

}
