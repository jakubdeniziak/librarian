import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Books} from "../model/books";
import {BookDetails} from "../model/book-details";
import {BookForm} from "../model/book-form";
import {LibraryBooks} from "../../library/model/library-book/library-books";
import {Endpoints} from "../../endpoints";

@Injectable()
export class BookService {

    constructor(private http: HttpClient) {
    }

    getBooks(): Observable<Books> {
        return this.http.get<Books>(Endpoints.BOOKS);
    }

    getBook(uuid: string): Observable<BookDetails> {
        return this.http.get<BookDetails>(Endpoints.BOOKS + '/' + uuid);
    }

    getBooksByAuthor(uuid: string): Observable<Books> {
        return this.http.get<Books>(Endpoints.AUTHORS + '/' + uuid + '/books')
    }

    getBooksByPublisher(uuid: string): Observable<Books> {
        return this.http.get<Books>(Endpoints.PUBLISHERS + '/' + uuid + '/books')
    }

    getBooksByLibrary(uuid: string): Observable<LibraryBooks> {
        return this.http.get<LibraryBooks>(Endpoints.LIBRARIES + '/' + uuid + '/books')
    }

    getBooksCount(): Observable<number> {
        return this.http.get<number>(Endpoints.BOOKS + "/count");
    }

    putBook(uuid: string, request: BookForm): Observable<any> {
        return this.http.put(Endpoints.BOOKS + '/' + uuid, request);
    }

    deleteBook(uuid: string): Observable<any> {
        return this.http.delete(Endpoints.BOOKS + '/' + uuid)
    }

}
