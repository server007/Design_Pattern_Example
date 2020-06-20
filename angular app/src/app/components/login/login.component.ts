import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/authentication.service';
import { AppComponent } from 'src/app/app.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    
  username='';
  password='';
  user :User=new User();
  invalidLogin=false;
  errorMessage='Invalid Credentials'
  successMessage: string;
  loginSuccess=false;
  constructor(private router: Router, private loginservice:AuthenticationService,private appComp:AppComponent) { }

  ngOnInit(): void {
  }

  checkLogin()
  {
     this.loginservice.authentication(this.user)
     .subscribe(data => {
      if(data!=0)
    {
      // console.log(data)
      // sessionStorage.setItem("email",this.user.email);
      sessionStorage.setItem("uid",data);
      // localStorage.setItem("token","valid");
      this.router.navigate(['/portal']);
      this.invalidLogin=false;
      this.loginSuccess=true;
      this.successMessage='Login Successful';
      // this.appComp.bool2=true;
      // this.loading.loadit=true;
    }
    else
    {
      this.invalidLogin=true;
      this.loginSuccess=false;
    }
    }, error => console.log(error));

  }

}
