import {Injectable} from '@angular/core';
import {LibraryBookForm} from "@features/library-book/models/library-book.form";
import {Observable} from "rxjs";
import {Endpoints} from "../../../endpoints";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LibraryBookService {
  constructor(private http: HttpClient) {
  }

  public addBookToLibrary(libraryId: string, bookId: string, request: LibraryBookForm): Observable<any> {
    return this.http.put(`${Endpoints.LIBRARIES}/${libraryId}/books/${bookId}`, request);
  }
}
