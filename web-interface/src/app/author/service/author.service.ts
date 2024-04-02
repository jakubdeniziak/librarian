import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Authors} from "../model/authors";
import {AuthorDetails} from "../model/author-details";

@Injectable()
export class AuthorService {
    constructor(private http: HttpClient) {
    }

    getAuthors(): Observable<Authors> {
        return this.http.get<Authors>('/api/authors');
    }

    getAuthor(uuid: string): Observable<AuthorDetails> {
        return this.http.get<AuthorDetails>('/api/authors/' + uuid);
    }
}
