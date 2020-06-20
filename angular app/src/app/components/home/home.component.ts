import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import {Notification} from 'src/app/notification';
import { AppComponent } from 'src/app/app.component';
import { AuthenticationService } from 'src/app/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  username='';
  password='';
  all=false;
  user :User=new User();
  invalidLogin=false;
  errorMessage='Invalid Email';
  nameof:string;

  notif :Notification=new Notification();
  notTypes:String[]=["Event", "UrgentHelp", "Holiday", "NagarroNews", "NewPolicy"];
  notType:String;

  constructor(private router: Router, private loginservice:AuthenticationService,private appComp:AppComponent) { }

  ngOnInit(): void {
  }
    submitEmail(){
    console.log(this.user.email);
    // if(this.user.email=="s@nagarro.com")
    // this.invalidLogin=false;
    // else
    this.invalidLogin=true
    }

    toggle(){
        this.all=true;
      }

      toggle2(){
        this.all=false;
      }

    send(){
      this.notif.recipientName = this.nameof;
      // .split(',');
      console.log(this.notif.recipientName);
      this.sendNotif();
    }

    sendNotif(){
      this.loginservice.sendNotif(this.notif)
     .subscribe(data => {
      if(data!=0)
    {
      console.log(data)
      this.router.navigate(["/login"]);
    }
    else
    {
    }
    }, error => console.log(error));

  }
}
