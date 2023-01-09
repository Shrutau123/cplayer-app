import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { Favs } from './fav';

@Injectable({
  providedIn: 'root'
})

// service to access the methods of cric api (www.cricapi.com)
export class CricapiService {

  // dependency injection of HttpClient service
  constructor(private http: HttpClient) {

   }

  // generated Api key from cricapi (old=dLGeON2q1aTVihU2hxeInpYKUNM2) api free = 8fb69166-edb4-4131-9d3e-56686ac721a4
  apikey: string = "4b6c9fee-b4d3-4b39-b1a1-933b7a0c38d1";

  // Get method to search players with the help of Player Name
  searchPlayer(name: string): Observable<any> {
    return this.http.get(`https://api.cricapi.com/v1/players?apikey=${this.apikey}`, {
      params: new HttpParams().set("search", name)
    });
  }

  // Get method to get the statistics of a particular player with the help of his pid
  statsPlayer(pid: any): Observable<any> {
    return this.http.get(`https://api.cricapi.com/v1/players_info?apikey=${this.apikey}`, {
      params: new HttpParams().set("id", pid)
    })
  }
  
  
  // Get the schedule of upcoming matches
  matchcalendar(): Observable<any> {
    return this.http.get<any>(`https://api.cricapi.com/v1/matches?apikey=${this.apikey}`)
  }

  // Get sports news
  newsSports():Observable<any>{
    return this.http.get<any>('http://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=144d87b6f02944989fc3e65deaae19a6')
  }

  pickPlayerStatsResults(response) {
    console.log(response);
    return response;
  }


}
