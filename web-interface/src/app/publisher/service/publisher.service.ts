import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Publishers} from "../model/publishers";
import {Publisher} from "../model/publisher";

@Injectable()
export class PublisherService {
    constructor(private http: HttpClient) {
    }

    getPublishers(): Observable<Publishers> {
        return this.http.get<Publishers>('/api/publishers');
    }

    getPublisher(uuid: string): Observable<Publisher> {
        return this.http.get<Publisher>('/api/publishers/' + uuid);
    }
}
