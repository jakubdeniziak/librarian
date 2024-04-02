import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Authors} from "../model/authors";
import {Author} from "../model/author";

@Injectable()
export class AuthorService {
    constructor(private http: HttpClient) {
    }

    getAuthors(): Observable<Authors> {
        return this.http.get<Authors>('/api/authors');
    }

    getAuthor(uuid: string): Observable<Author> {
        return this.http.get<Author>('/api/authors/' + uuid);
    }
}
