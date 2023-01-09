import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Recommended } from './recommended';

@Injectable({
  providedIn: 'root'
})
// service to access all the backend api's of Recommended Microservice
export class RecommendedService {

  // dependency injection of HTTPCLIENT dervice
  constructor(private http: HttpClient) { }

  // Get method to get all the users which passes the recommended criterea
  // if a player is added to favs by more than 5 users then that player will be shown in recommended
  getData(token: string): Observable<any> {
    return this.http.get<any>(`http://localhost:8888/api/recom`, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // Post method to add recommended so that the counter can increase by 1
  addData(recom: Recommended, token: string): Observable<any> {
    return this.http.post<any>(`http://localhost:8888/api/recom`, recom, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // Delete method to delete recommended so that the counter can be decremented by 1
  deleteData(pid: number, token: string): Observable<any> {
    return this.http.delete<any>(`http://localhost:8888/api/recom?id=${pid}`, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

}
