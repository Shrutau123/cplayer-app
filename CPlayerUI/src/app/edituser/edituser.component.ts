import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '../user';
import { UserAuth } from '../userAuth';
import { AuthenticationService } from '../authentication.service';
import { UserService } from '../user.service';
import { RouterService } from '../router.service';
import { FavouritesService } from '../favourites.service';

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
// imlementation of all edit user functionalities
export class EdituserComponent implements OnInit {

  token: string;
  nam: string;
  val: string;

  user: User = new User();
  userauth: UserAuth = new UserAuth();

  // dependency injection of required services
  constructor(private route: RouterService, private userser: UserService,
     private auth: AuthenticationService, private favser : FavouritesService) { }

  ngOnInit(): void {

    if (sessionStorage.getItem('token') == null || sessionStorage.getItem('username') == null) {
      this.route.tologin();
    }

    this.nam = sessionStorage.getItem('username');
    this.token = sessionStorage.getItem('token');

    // get userdetails from DB at the time of initialization
    this.userser.getdetails(this.nam, this.token).subscribe(
      res => this.user = res,
      err => console.log(err)
    )
  }

  // form validation of mobile fields
  mobileForm = new FormGroup({
    mobile: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]),
  })

  // form validation for password fields
  passwordForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required, Validators.minLength(8)]),
    newPassword: new FormControl('', [Validators.required, Validators.minLength(8)]),
  })

  get mobile() {
    return this.mobileForm.get('mobile');
  }
  get oldPassword() {
    return this.passwordForm.get('oldPassword');
  }
  get newPassword() {
    return this.passwordForm.get('newPassword');
  }

  img: string;

  // method to change the browsed image of user into string
  onFileChanged(event) {
    const file = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (_event => {
      this.img = reader.result.toString();
    })
  }

  // method to change the image of the user
  image() {
    this.user.image = this.img;
    console.log(this.user);
    this.userser.modifyUser(this.user, this.token).subscribe(
      res => console.log(res),
      err => {
        if (err.statusText === "OK") {
          console.log("success");
          this.route.back();
        }
      }
    )
  }

  // method to make a request for changing the mobile number
  mobiles() {
    this.user.mobile = this.mobileForm.value.mobile;
    console.log(this.user);
    console.log(this.token);

    this.userser.modifyUser(this.user, this.token).subscribe(
      res => console.log(res),
      err => {
        if (err.statusText === "OK") {
          console.log("success");
          this.route.back();
        }
      }
    )
  }

  // method to take old and new pass and send a request to change the password
  password() {
    this.userauth.password = this.passwordForm.value.oldPassword;
    this.userauth.username = this.nam;
    console.log(this.userauth);
    console.log(this.token);
    this.auth.updateUser(this.userauth, this.passwordForm.value.newPassword, this.token).subscribe(
      res => console.log(res),
      err => {
        if (err.statusText === "OK") {
          sessionStorage.clear();
          this.route.tologin();
        }
      }
    )
  }

  // delete all user info from all the databases(user, userauth and favourites also)
  deleteUser(val){
    this.userser.deleteUser(sessionStorage.getItem('username'),sessionStorage.getItem('token')).subscribe();
    this.auth.deleteUser(sessionStorage.getItem('username'),sessionStorage.getItem('token')).subscribe();
    this.favser.deleteData(sessionStorage.getItem('username'),sessionStorage.getItem('token')).subscribe();
    sessionStorage.clear();
    this.route.tologin();
  }

}
