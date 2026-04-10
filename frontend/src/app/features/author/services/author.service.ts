import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Authors} from "../models/authors.model";
import {AuthorDetails} from "../models/author-details.model";
import {AuthorForm} from "../models/author-form.model";
import {Endpoints} from "@app/endpoints";
import {PagedResult} from "@shared/models/paged-result.model";
import {Author} from "@features/author/models/author.model";
import {AuthorsQuery} from "@features/author/models/authors-query.model";
import {AuthorInsights} from "@features/author/models/author-insights.model";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private http: HttpClient) {
  }

  public getMyAuthorsPaged(query: AuthorsQuery): Observable<PagedResult<Author>> {
    return this.http.get<PagedResult<Author>>(Endpoints.AUTHORS_ME, {
      params: this.toHttpParams(query)
    });
  }

  public getDiscoverAuthorsPaged(query: AuthorsQuery): Observable<PagedResult<Author>> {
    return this.http.get<PagedResult<Author>>(Endpoints.AUTHORS, {
      params: this.toHttpParams(query)
    });
  }

  public getMyAuthorInsights(): Observable<AuthorInsights> {
    return this.http.get<AuthorInsights>(Endpoints.AUTHORS_INSIGHTS_ME);
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

  private toHttpParams(query: AuthorsQuery): HttpParams {
    let params = new HttpParams()
      .set('page', String(query.page))
      .set('size', String(query.size));
    if (query.q) params = params.set('q', query.q);
    if (query.sort) params = params.set('sort', query.sort);
    return params;
  }
}
