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
      this.getIssuer(categoryId);
    });
  }

  private getLocalTimeString(): string{
    const d = new Date();
    const value = d.toLocaleTimeString();
    return value.substring(0,2) + value.substring(3,5);
  }

  private getIfOpenOrNot(): boolean{
    const day = this.getToday();
    let start = "";
    let end = "";
    let from = 0;
    let to = 0;
    let q = 0;

    if(day == "Saturday"){
      start = this.distributor.saturday.substring(0, 2) + this.distributor.saturday.substring(3, 5);
      end = this.distributor.saturday.substring(6, 8) + this.distributor.saturday.substring(9, 12);
    }
    else if(day == "Sunday"){
      start = this.distributor.sunday.substring(0, 2) + this.distributor.sunday.substring(3, 5);
      end = this.distributor.sunday.substring(6, 8) + this.distributor.sunday.substring(9, 12);
    }
    else{
      start = this.distributor.weekdays.substring(0, 2) + this.distributor.weekdays.substring(3, 5);
      end = this.distributor.weekdays.substring(6, 8) + this.distributor.weekdays.substring(9, 12);
    }

    from = parseInt(start);
    to = parseInt(end);
    q = parseInt(this.getLocalTimeString());
    return q >= from && q <= to;
  }

  private getToday(): string{
    const weekday = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];

    const d = new Date();
    return weekday[d.getUTCDay()];
  }

  getIssuer(id: string){
    this.service.getDistributor(id).subscribe(response => {
      this.distributor = response;
      this.isOpen = this.getIfOpenOrNot();
      console.log(response);
    }, error => {
      console.log(error);
    });
  }
}
