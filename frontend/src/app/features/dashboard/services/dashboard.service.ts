import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Endpoints} from '@app/endpoints';
import {DashboardResponse} from '../models/dashboard.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  constructor(private http: HttpClient) {
  }

  public getMyDashboard(): Observable<DashboardResponse> {
    return this.http.get<DashboardResponse>(Endpoints.ME_DASHBOARD);
  }
}
