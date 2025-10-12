import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Authors} from "../models/authors.model";
import {AuthorDetails} from "../models/author-details.model";
import {AuthorForm} from "../models/author-form.model";
import {Endpoints} from "../../../endpoints";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private http: HttpClient) {
  }

  public putAuthor(uuid: string, request: AuthorForm): Observable<void> {
    return this.http.put<void>(`${Endpoints.AUTHORS}/${uuid}`, request);
  }

  public getAuthor(uuid: string): Observable<AuthorDetails> {
    return this.http.get<AuthorDetails>(`${Endpoints.AUTHORS}/${uuid}`);
  }

  public getAuthors(): Observable<Authors> {
    return this.http.get<Authors>(Endpoints.AUTHORS);
  }

  public getAuthorsCount(): Observable<number> {
    return this.http.get<number>(`${Endpoints.AUTHORS}/count`);
  }

  public deleteAuthor(uuid: string): Observable<void> {
    return this.http.delete<void>(`${Endpoints.AUTHORS}/${uuid}`);
  }
}
