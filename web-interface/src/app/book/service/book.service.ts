import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Books} from "../model/books";
import {BookDetails} from "../model/book-details";
import {BookForm} from "../model/book-form";

@Injectable()
export class BookService {
    constructor(private http: HttpClient) {
    }

    getBooks(): Observable<Books> {
        return this.http.get<Books>('/api/books')
    }

    getBook(uuid: string): Observable<BookDetails> {
        return this.http.get<BookDetails>('/api/books/' + uuid);
    }


    getBooksByAuthor(uuid: string): Observable<Books> {
        return this.http.get<Books>('/api/authors/' + uuid + '/books')
    }

    getBooksByPublisher(uuid: string): Observable<Books> {
        return this.http.get<Books>('/api/publishers/' + uuid + '/books')
    }

    putBook(uuid: string, request: BookForm): Observable<any> {
        return this.http.put('/api/books/' + uuid, request);
    }
}
