import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Publishers} from "../model/publishers";

@Injectable()
export class PublisherService {
    constructor(private http: HttpClient) {
    }

    getPublishers(): Observable<Publishers> {
        return this.http.get<Publishers>('/api/publishers')
    }
}
