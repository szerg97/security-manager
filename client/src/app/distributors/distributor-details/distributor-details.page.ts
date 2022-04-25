import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuController, NavController } from '@ionic/angular';
import { Issuer } from 'src/app/_models/issuer';
import { AuthService } from 'src/app/_services/auth.service';
import { DistributorService } from 'src/app/_services/distributor.service';

@Component({
  selector: 'app-distributor-details',
  templateUrl: './distributor-details.page.html',
  styleUrls: ['./distributor-details.page.scss'],
})
export class DistributorDetailsPage implements OnInit {

  distributor: Issuer;

  isOpen: boolean;

  isAdmin: boolean;

  constructor(
    private service: DistributorService,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService
    ) {
    }

  ngOnInit() {
    this.isAdmin = this.authService.getUserRoles.includes('gsec');
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const categoryId = paramMap.get('id');
      this.getCategory(categoryId);
    });
    this.isOpen = this.getIfOpenOrNot();
  }

  getIfOpenOrNot(): boolean{
    const day = this.getToday();
    if(day == "Saturday"){
      return true;
    }
    else if(day == "Sunday"){
      return true;
    }
    else{
      return true;
    }
  }

  getToday(): string{
    const weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];

    const d = new Date();
    return weekday[d.getUTCDay()];
  }

  getCategory(id: string){
    this.service.getDistributor(id).subscribe(response => {
      this.distributor = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
  }
}
