import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { RouterService } from '../router.service';
import { AuthenticationService } from '../authentication.service';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

  user: User = new User();

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  // dependency injection of required services
  constructor(private breakpointObserver: BreakpointObserver, private route: RouterService,
     private auth : AuthenticationService, private userser :UserService) {}

  ngOnInit(): void {
    // check if the username and token are set, otherwise redirect to login page
    if (sessionStorage.getItem('token') == null || sessionStorage.getItem('username') == null) {
      this.route.tologin();
    }
    var name:string = sessionStorage.getItem('username');
    var token:string = sessionStorage.getItem('token');    

    // if not able to fetch user details from database then logout
    this.userser.getdetails(name,token).subscribe(
      res => this.user=res,
      err => {
        sessionStorage.clear();
        this.route.tologin();
      }
    )

  }

  // log Out any logged in user 
  logout(){
    sessionStorage.clear();
    this.route.tologin();
  }

}
