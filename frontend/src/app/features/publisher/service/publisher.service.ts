import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Publishers} from "../models/publishers.model";
import {PublisherDetails} from "../models/publisher-details.model";
import {PublisherForm} from "../models/publisher-form.model";
import {Endpoints} from "../../../endpoints";

@Injectable({
  providedIn: 'root'
})
export class PublisherService {
  constructor(private http: HttpClient) {
  }

  public putPublisher(uuid: string, request: PublisherForm): Observable<any> {
    return this.http.put<void>(`${Endpoints.PUBLISHERS}/${uuid}`, request);
  }

  public getPublisher(uuid: string): Observable<PublisherDetails> {
    return this.http.get<PublisherDetails>(`${Endpoints.PUBLISHERS}/${uuid}`);
  }

  public getPublishers(): Observable<Publishers> {
    return this.http.get<Publishers>(Endpoints.PUBLISHERS);
  }

  public getPublishersCount(): Observable<number> {
    return this.http.get<number>(`${Endpoints.PUBLISHERS}/count`);
  }

  public deletePublisher(uuid: string): Observable<void> {
    return this.http.delete<void>(`${Endpoints.PUBLISHERS}/${uuid}`);
  }
}
