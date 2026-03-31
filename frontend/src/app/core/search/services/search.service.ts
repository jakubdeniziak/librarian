import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SearchRequest, SearchResponse} from '@core/search/models/search.model';
import {Endpoints} from "../../../endpoints";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  constructor(private http: HttpClient) {
  }

  public search(request: SearchRequest): Observable<SearchResponse> {
    return this.http.get<SearchResponse>(Endpoints.SEARCH, {
      params: this.toParams(request)
    });
  }

  private toParams(request: SearchRequest): HttpParams {
    let params = new HttpParams();

    const put = (key: string, value: string | number | boolean | undefined | null) => {
      if (value === undefined || value === null || value === '') {
        return;
      }
      params = params.set(key, String(value));
    };

    put('query', request.query);
    put('includeBooks', request.includeBooks);
    put('includeAuthors', request.includeAuthors);
    put('includePublishers', request.includePublishers);
    put('includeLibraries', request.includeLibraries);

    put('libraryId', request.libraryId);
    put('language', request.language);
    put('format', request.format);
    put('yearFrom', request.yearFrom);
    put('yearTo', request.yearTo);
    put('page', request.page);
    put('size', request.size);

    return params;
  }
}
