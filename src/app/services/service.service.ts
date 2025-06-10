import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Service } from '../models/service.model';
import { SousService } from '../models/sous-service.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  // Méthodes existantes...

  addService(service: Service): Observable<Service> {
    return this.http.post<Service>(`${this.apiUrl}/services`, service);
  }

  addSousService(sousService: SousService): Observable<SousService> {
    return this.http.post<SousService>(`${this.apiUrl}/services/${sousService.serviceId}/sous-services`, sousService);
  }
}