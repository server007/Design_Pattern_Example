import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { Router, RouterOutlet } from '@angular/router';
import { AuthenticationService } from 'src/app/authentication.service';
import { AppComponent } from 'src/app/app.component';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  username='';
  password='';
  user :User=new User();
  invalidLogin=false;
  successMessage: string;
  loginSuccess=false;
  constructor(private router: Router, private loginservice:AuthenticationService,private appComp:AppComponent) { }

  ngOnInit(): void {
  }

  register()
  {
     this.loginservice.newUser(this.user)
     .subscribe(data => {
      if(data!=0)
    {
      // sessionStorage.setItem("email",this.user.email);
      this.loginSuccess=true;
      this.successMessage='Login Successful';
      // this.appComp.bool2=true;
      // this.loading.loadit=true;
      this.routeTo();
    }
    else
    {
      this.loginSuccess=false;
    }
    }, error => console.log(error));
  }
  routeTo(){
      this.router.navigate(['/login']);
  }
}
