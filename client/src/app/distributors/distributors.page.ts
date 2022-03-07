import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonItemSliding, LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Distributor } from '../_models/distributor';
import { DistributorService } from '../_services/distributor.service';

@Component({
  selector: 'app-distributors',
  templateUrl: './distributors.page.html',
  styleUrls: ['./distributors.page.scss'],
})
export class DistributorsPage implements OnInit, OnDestroy {

  loadedDistributors: Distributor[];
  isLoading = false;
  private distributorsSub: Subscription;

  constructor(
    public distributorService: DistributorService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController) 
    {}

  ngOnInit() {
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
    });
  }

  ngOnDestroy() {
    if (this.distributorsSub) {
      this.distributorsSub.unsubscribe();
    }
  }
}
