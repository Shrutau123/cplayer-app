import { Component, OnInit } from '@angular/core';
import { CricapiService } from '../cricapi.service';
import { Find } from '../find';
import { FavouritesService } from '../favourites.service';
import { Favs } from '../fav';
import { Recommended } from '../recommended';
import { RecommendedService } from '../recommended.service';
import { RouterService } from '../router.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

// search player by name functionality
export class SearchComponent implements OnInit {

  stat: boolean;
  config: any;
  val: string;
  fav: Favs = new Favs;
  fav2: Favs;
  recom: Recommended = new Recommended;
  Id;
  list: Array<Find> = [];

  // Dependency Injection of cric api, fav service and recommended service
  constructor(private cricapi: CricapiService, private favser: FavouritesService,
    private recomser: RecommendedService, private route: RouterService) {
    this.val = "";
    // paginantion
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: this.list.length
    };

  }

  // Page events are stored in config
  pageChanged(event) {
    this.config.currentPage = event;
  }
  ngOnInit() {
    if (sessionStorage.getItem('token') == null || sessionStorage.getItem('username') == null) {
      this.route.tologin();
    }

  }

  // myString(str:string){
  //   var matches = str.replace("-","").match(/(\d+)/);
  //   return matches;
  // }

  // It will call cric api service and get list of players
  getData(val) {
    console.log(val);
    this.cricapi.searchPlayer(val).subscribe(
      res => {
        this.list = res.data;
        for (let obj of this.list) {
          obj.status = true;
        }
        console.log(this.list);
      },
      err => {
        console.log(err)
      })
  }
  onView(id) {
    this.Id = id;
    // console.log(id);
    // console.log(this.Id);

    sessionStorage.setItem('fId', this.Id);

    console.log(sessionStorage.getItem('fId'));
  }

  // it will add a player into recommended as well as the favourites by calling there respective services
  addToFav(data) {
    data.status = false;
    this.cricapi.statsPlayer(sessionStorage.getItem('fId')).subscribe(
      (res: any) => {
        console.log(res['data']['dateOfBirth']);
        //         this.fav = new Favs(sessionStorage.getItem('username'),sessionStorage.getItem('fId'),res['data']['country'],res['data']['name'],res['data']['role'],res['data']['placeOfBirth'],res['data']['dateOfBirth'],res['data']['playerImg'],false)
        // console.log(this.fav);
        this.fav.dateOfBirth = (res['data']['dateOfBirth']);
        this.fav.name = res['data']['name'];
        this.fav.placeOfBirth = res['data']['placeOfBirth'];
        this.fav.playerImg = res['data']['playerImg'];
        this.fav.role = res['data']['role'];
        // this.fav.id = "1";
        this.fav.status = false;
        this.fav.country = res['data']['country'];
        this.fav.username = sessionStorage.getItem('username');
        this.fav.pid = (sessionStorage.getItem('fId'));

        console.log(this.fav);
        console.log(this.fav.pid);

        // this.recom = res;
        this.recom.dateOfBirth = (res['data']['dateOfBirth']);
        this.recom.name = res['data']['name'];
        this.recom.placeOfBirth = res['data']['placeOfBirth'];
        this.recom.playerImg = res['data']['playerImg'];
        this.recom.role = res['data']['role'];
        // this.fav.id = "1";
        // this.recom.status = false;
        this.recom.country = res['data']['country'];
        // this.recom.username = sessionStorage.getItem('username');
        this.recom.pid = (sessionStorage.getItem('fId'));


        this.recomser.addData(this.recom, sessionStorage.getItem('token')).subscribe(
          res => console.log("added to recom" + res),
          err => console.log(err)
        )
        this.favser.addData(this.fav, sessionStorage.getItem('token')).subscribe(
          res => console.log("added to fav" + res),
          err => console.log(err)
        )
      },
      err => console.log(err)
    )
  }

  // it will remove a player from recommended as well as the favourites by calling there respective services
  removeFromFav(data) {
    data.status = true;
    this.recomser.deleteData(data.pid, sessionStorage.getItem('token')).subscribe(
      res => console.log("removed from fav"),
      err => console.log(err)
    )
    this.favser.deleteDataUser(sessionStorage.getItem('username'), data.name, sessionStorage.getItem('token')).subscribe(
      res => console.log("removed from recom"),
      err => console.log(err)
    )
  }

}
