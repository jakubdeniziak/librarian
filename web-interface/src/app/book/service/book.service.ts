import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Books} from "../model/books";

@Injectable()
export class BookService {
    constructor(private http: HttpClient) {
    }

    getBooks(): Observable<Books> {
        return this.http.get<Books>('/api/books')
    }
}
