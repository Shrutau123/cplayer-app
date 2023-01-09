import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { StatOpenerComponent } from '../stat-opener/stat-opener.component';
import { CricapiService } from '../cricapi.service';
import { RouterService } from '../router.service';
import { Find } from '../find';
import { Favs } from '../fav';

@Component({
  selector: 'app-statview',
  templateUrl: './statview.component.html',
  styleUrls: ['./statview.component.css']
})
export class StatviewComponent implements OnInit {
// player:Find;
  stat :any;
  // Id : string;
  // cPlayerStats:Favs;
  // actionType: string;
  // dependency injection of cricapi service and also using @Inject to specify that some the value is injected
  constructor(private diaRef: MatDialogRef<StatOpenerComponent>, @Inject(MAT_DIALOG_DATA) private data: any,
   private cric: CricapiService, private route :RouterService) { 
    // this.player = data.obj;
    // this.Id = data.obj.pid;
    // this.actionType = data.actionType;
   }

  ngOnInit(): void {

    if (sessionStorage.getItem('token') == null || sessionStorage.getItem('username') == null) {
      this.route.tologin();
    }
    // this.Id = sessionStorage.getItem('id');
    // call api to get all the stats of a particular player by his pid
    console.log("Call the Cric Player ApI based on PID to get player stats.");
    
    // this.cric.statsPlayer(this.Id).subscribe((cPlayerStats)=>{
    //   this.cPlayerStats=cPlayerStats;
    //   console.log("Fetch player stats.");});



    console.log(this.data);
    console.log(sessionStorage.getItem('pId'));
    this.cric.statsPlayer(sessionStorage.getItem('pId')).subscribe(
      res => {
        // this.stat = res
        this.stat = res['data']

        console.log(this.stat)

      },
      err => console.log(err)
    )
  }

}
