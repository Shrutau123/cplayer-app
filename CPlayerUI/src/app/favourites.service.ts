import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Favs } from './fav';

@Injectable({
  providedIn: 'root'
})
// service to access the Favourites Microservice of backend and all the api's
export class FavouritesService {

  // dependency injection of http client
  constructor(private http: HttpClient) { }

  // Get method to get the favourites of a particular user by the help of its username http://localhost:8000/api/fav
  getData(usern: String, token: string): Observable<any> {
    return this.http.get<any>(`http://localhost:8888/api/fav?username=${usern}`, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // Post method to add a player to favourites
  addData(fav: Favs, token: string): Observable<any> {
    return this.http.post<any>(`http://localhost:8888/api/fav`, fav, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // Delete method to remove all the players from the favourites of a particular user
  deleteData(usern: string, token: string): Observable<any> {
    return this.http.delete<any>(`http://localhost:8888/api/fav?username=${usern}`, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // method to delete a particular player from favourites by a particular user
  deleteDataUser(usern: string, name: string, token: string): Observable<any> {
    return this.http.post<any>(`http://localhost:8888/api/fav/id`, { "username": `${usern}`, "name": `${name}` }, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

}
