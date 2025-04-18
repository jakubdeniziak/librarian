import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Authors} from "../model/authors";
import {AuthorDetails} from "../model/author-details";
import {AuthorForm} from "../model/author-form";
import {Endpoints} from "../../endpoints";

@Injectable()
export class AuthorService {

    constructor(private http: HttpClient) {
    }

    getAuthors(): Observable<Authors> {
        return this.http.get<Authors>(Endpoints.AUTHORS);
    }

    getAuthor(uuid: string): Observable<AuthorDetails> {
        return this.http.get<AuthorDetails>(Endpoints.AUTHORS + '/' + uuid);
    }

    putAuthor(uuid: string, request: AuthorForm): Observable<any> {
        return this.http.put(Endpoints.AUTHORS + '/' + uuid, request);
    }

    deleteAuthor(uuid: string): Observable<any> {
        return this.http.delete(Endpoints.AUTHORS + '/' + uuid)
    }

}
