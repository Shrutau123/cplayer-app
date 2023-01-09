import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UserAuth } from './userAuth';
import { UserService } from './user.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
// service to implement the User authentication Backend Microservice and its api's
export class AuthenticationService {

  // dependency injection of HttpClient (http://50.23.1.37:32000/api/auth/register)
  constructor(private httpClient: HttpClient) { }

  // Method to register a new user into the authentication database
  public signup(userauth: UserAuth): Observable<any> {
    return this.httpClient.post<UserAuth>('http://localhost:8888/api/auth/register', userauth).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // Method to set bearer token into Session Storage
  setBearerToken(token: string) {
    sessionStorage.setItem('token', token);
  }

  // Method to get the bearer token from the Session Storage
  getBearerToken() {
    return sessionStorage.getItem('token');
  }

  // Method to validate a user and create a JWT tokan for him
  public login(userinfo: UserAuth): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8888/api/auth/login', userinfo).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // Method to delete user details of a particular user
  public deleteUser(username: string, token: string): Observable<any> {
    return this.httpClient.delete<any>(`http://localhost:8888/api/auth/pro/user?username=${username}`, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

  // method to update the password of a particular user
  public updateUser(user: UserAuth, pass: string, token: string): Observable<any> {
    return this.httpClient.put<any>(`http://localhost:8888/api/auth/pro/user`,{"username":`${user.username}`,"oldpass":`${user.password}`,"newpass":`${pass}`} ,{
      headers: new HttpHeaders().set("Authorization", `Bearer ${token}`)
    }).pipe(
      map(
        userData => {
          return userData;
        }));
  }

}
