import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  //dependency injection of Router and Location services
  constructor(private router: Router, private location: Location) { }

  // method to go to login form
  tologin(){
    this.router.navigate(["/login"])
  }

  // method to go to sign up form
  tosignup(){
    this.router.navigate(["/signup"])
  }

  // method to load the dashboard component
  todashboard(){
    this.router.navigate(["/dashboard"])
  }

  // method to load the contact component
  tocontact(){
    this.router.navigate(["/contact"])
  }

  // method to redirect to fav component
  tofav(){
    this.router.navigate(["/dashboard/favs"])
  }

  tohelp(){
    this.router.navigate(["/dashboard/help"])
  }
  // method to navigate to stat opener with the player pid whose statistics are to be shown
  tostatOpener(pid){
    this.router.navigate(['dashboard',{
      outlets:{
        statOpener:['statOpener',pid,'view']
      }
    }])
  }

  // method to get to the previous component
  back(){
    this.location.back();
  }
}
