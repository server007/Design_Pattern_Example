import { Component, OnInit, ɵCodegenComponentFactoryResolver } from '@angular/core';
import { User } from 'src/app/user';
import { AppComponent } from 'src/app/app.component';
import { AuthenticationService } from 'src/app/authentication.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-portal',
  templateUrl: './portal.component.html',
  styleUrls: ['./portal.component.css']
})
export class PortalComponent implements OnInit {

  uid;
  data2; 
  // = [
  //   [{id:1,name:'name1'},{id:2,name:'name2'}],
  //   [{id:3,name:'name3'},{id:4,name:'name4'}],
  //   [{id:5,name:'name5'},{id:6,name:'name6'}]
  // ];
  constructor(private router: Router, private loginservice:AuthenticationService,private appComp:AppComponent) { }

  ngOnInit(): void {

    this.uid=sessionStorage.getItem('uid');
    // console.log(this.uid);
    this.getData(this.uid);
  }

  getData(uid)
  {
     this.loginservice.getData(uid)
     .subscribe(data => {
       console.log(data)
      if(data!=null)
    {
      console.log(data);
      this.data2=data;
      // sessionStorage.setItem("email",this.user.email);
      // this.appComp.bool2=true;
      // this.loading.loadit=true;
    }
    else
    {
    }
    }, error => console.log(error));

  }

  clearNotif(item){
    console.log(item.nId)
    this.loginservice.removeData(item.nId,this.uid)
    .subscribe(data=>{
      console.log(data)
      this.data2=data;
    },error=> console.log(error));
  }

  logout(){
    sessionStorage.removeItem('uid')
  }
  
}
