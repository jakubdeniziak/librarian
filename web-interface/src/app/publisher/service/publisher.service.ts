import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Publishers} from "../model/publishers";
import {PublisherDetails} from "../model/publisher-details";
import {PublisherForm} from "../model/publisher-form";

@Injectable()
export class PublisherService {
    constructor(private http: HttpClient) {
    }

    getPublishers(): Observable<Publishers> {
        return this.http.get<Publishers>('/api/publishers');
    }

    getPublisher(uuid: string): Observable<PublisherDetails> {
        return this.http.get<PublisherDetails>('/api/publishers/' + uuid);
    }

    putPublisher(uuid: string, request: PublisherForm): Observable<any> {
        return this.http.put('/api/publishers/' + uuid, request);
    }
}
