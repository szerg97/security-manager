import { Component, OnInit } from '@angular/core';
import { LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Transaction } from 'src/app/_models/transaction';
import { AuthService } from 'src/app/_services/auth.service';
import { SecurityService } from 'src/app/_services/security.service';

@Component({
  selector: 'app-purchased',
  templateUrl: './purchased.page.html',
  styleUrls: ['./purchased.page.scss'],
})
export class PurchasedPage implements OnInit {

  loadedSecurities: Transaction[];
  isLoading = false;
  private securitiesSub: Subscription;

  constructor(
    private securityService: SecurityService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController
    ) { }

    ngOnInit() {
      this.securitiesSub = this.securityService.securities.subscribe(sec => {
        this.loadedSecurities = sec;
        this.menuCtrl.enable(true);
      })
    }
  
    ionViewWillEnter() {
      this.isLoading = true;
      this.securityService.fetchSecuritiesByCustomer().subscribe(() => {
        this.isLoading = false;
      });
    }
  
    onOpenMenu() {
      this.menuCtrl.toggle();
    }
  
    ngOnDestroy() {
      if (this.securitiesSub) {
        this.securitiesSub.unsubscribe();
      }
    }

}
