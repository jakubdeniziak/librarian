import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Publishers} from "../model/publishers";
import {PublisherDetails} from "../model/publisher-details";
import {PublisherForm} from "../model/publisher-form";
import {Endpoints} from "../../endpoints";

@Injectable()
export class PublisherService {

    constructor(private http: HttpClient) {
    }

    getPublishers(): Observable<Publishers> {
        return this.http.get<Publishers>(Endpoints.PUBLISHERS);
    }

    getPublisher(uuid: string): Observable<PublisherDetails> {
        return this.http.get<PublisherDetails>(Endpoints.PUBLISHERS + "/" + uuid);
    }

    getPublishersCount(): Observable<number> {
        return this.http.get<number>(Endpoints.PUBLISHERS + "/count");
    }

    putPublisher(uuid: string, request: PublisherForm): Observable<any> {
        return this.http.put(Endpoints.PUBLISHERS + "/" + uuid, request);
    }

    deletePublisher(uuid: string): Observable<any> {
        return this.http.delete(Endpoints.PUBLISHERS + "/" + uuid)
    }

}
