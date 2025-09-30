import {Injectable} from '@angular/core';
import {LibrariesModel} from "@features/library/models/libraries.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LibraryDetailsModel} from "@features/library/models/library-details.model";
import {LibraryFormModel} from "@features/library/models/library-form.model";
import {Endpoints} from "../../../endpoints";

@Injectable({
  providedIn: 'root'
})
export class LibraryService {
  constructor(private http: HttpClient) {
  }

  public putLibrary(uuid: string, request: LibraryFormModel): Observable<any> {
    return this.http.put<void>(`${Endpoints.LIBRARIES}/${uuid}`, request);
  }

  public getLibrary(uuid: string): Observable<LibraryDetailsModel> {
    return this.http.get<LibraryDetailsModel>(`${Endpoints.LIBRARIES}/${uuid}`);
  }

  public getLibraries(): Observable<LibrariesModel> {
    return this.http.get<LibrariesModel>(Endpoints.LIBRARIES);
  }

  public getLibrariesCount(): Observable<number> {
    return this.http.get<number>(`${Endpoints.LIBRARIES}/count`);
  }

  public deleteLibrary(uuid: string): Observable<any> {
    return this.http.delete(`${Endpoints.LIBRARIES}/${uuid}`);
  }
}
