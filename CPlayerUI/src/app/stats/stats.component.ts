import { Component, OnInit } from '@angular/core';
import { Find } from '../find';
import { CricapiService } from '../cricapi.service';
import { RouterService } from '../router.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  config: any;
  val: string;
  list: Array<Find> = [];
  Id;


  constructor(private cricapi: CricapiService, private route: RouterService) {
    //pagination
    this.val = "";
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: this.list.length
    };

  }

  // note if the page is changed
  pageChanged(event) {
    this.config.currentPage = event;
  }
  ngOnInit() {

    if (sessionStorage.getItem('token') == null || sessionStorage.getItem('username') == null) {
      this.route.tologin();
    }

  }

  onView(id) {
    this.Id = id;
    console.log(id);
    console.log(this.Id);

    sessionStorage.setItem('pId', this.Id);


  }

  // this method will search player by his name as privided in val
  getData(val) {
    this.cricapi.searchPlayer(val).subscribe(
      res => {
        this.list = res.data;
        console.log(this.list);
        for (let obj of this.list) {
          obj.status = true;
        }
      },
      err => {
        console.log(err);


      })
  }

 

  // this method will redirect to stat Opener component by taking player id (pid) with it
  viewStats(data) {
    this.route.tostatOpener(data.pid);
    console.log(data.pid);
  }

}
