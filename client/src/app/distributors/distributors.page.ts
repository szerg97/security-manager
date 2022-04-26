import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonItemSliding, LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Issuer } from '../_models/issuer';
import { AuthService } from '../_services/auth.service';
import { DistributorService } from '../_services/distributor.service';

@Component({
  selector: 'app-distributors',
  templateUrl: './distributors.page.html',
  styleUrls: ['./distributors.page.scss'],
})
export class DistributorsPage implements OnInit, OnDestroy {

  loadedDistributors: Issuer[];
  isLoading = false;
  private distributorsSub: Subscription;
  isAdmin: boolean;

  constructor(
    public distributorService: DistributorService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController,
    private authService: AuthService
    ) 
    {}

  ngOnInit() {
    this.isAdmin = this.authService.getUserRoles.includes('gsec');
    this.distributorsSub = this.distributorService.distributors.subscribe(distributors => {
      this.loadedDistributors = distributors;
      this.menuCtrl.enable(true);
    })
  }

  onDeleteDistributor(id: string, slidingEl: IonItemSliding) {
    slidingEl.close();
    this.loadingCtrl.create({ message: 'Deleting...' }).then(loadingEl => {
      loadingEl.present();
      this.distributorService.deleteDistributor(id).subscribe(() => {
        loadingEl.dismiss();
      });
    });
  }

  ionViewWillEnter() {
    this.isLoading = true;
    this.distributorService.fetchDistributors().subscribe(() => {
      this.isLoading = false;
      
      this.loadedDistributors.forEach(i => {
        i.status = this.getIfOpenOrNot(i);
      });
    });
  }

  private getLocalTimeString(): string{
    const d = new Date();
    const value = d.toLocaleTimeString();
    return value.substring(0,2) + value.substring(3,5);
  }

  private getIfOpenOrNot(distributor: Issuer): boolean{
    const day = this.getToday();
    let start = "";
    let end = "";
    let from = 0;
    let to = 0;
    let q = 0;

    if(day == "Saturday"){
      start = distributor.saturday.substring(0, 2) + distributor.saturday.substring(3, 5);
      end = distributor.saturday.substring(6, 8) + distributor.saturday.substring(9, 12);
    }
    else if(day == "Sunday"){
      start = distributor.sunday.substring(0, 2) + distributor.sunday.substring(3, 5);
      end = distributor.sunday.substring(6, 8) + distributor.sunday.substring(9, 12);
    }
    else{
      start = distributor.weekdays.substring(0, 2) + distributor.weekdays.substring(3, 5);
      end = distributor.weekdays.substring(6, 8) + distributor.weekdays.substring(9, 12);
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

  ngOnDestroy() {
    if (this.distributorsSub) {
      this.distributorsSub.unsubscribe();
    }
  }
}
