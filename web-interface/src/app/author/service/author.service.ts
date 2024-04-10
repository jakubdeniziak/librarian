import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Authors} from "../model/authors";
import {AuthorDetails} from "../model/author-details";
import {AuthorForm} from "../model/author-form";

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

    putAuthor(uuid: string, request: AuthorForm): Observable<any> {
        return this.http.put('/api/authors/' + uuid, request);
    }

    deleteAuthor(uuid: string): Observable<any> {
        return this.http.delete('/api/authors/' + uuid)
    }
}
