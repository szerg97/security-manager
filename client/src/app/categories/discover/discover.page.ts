import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonItemSliding, LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Security } from 'src/app/_models/security';
import { AuthService } from 'src/app/_services/auth.service';
import { SecurityService } from 'src/app/_services/security.service';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.page.html',
  styleUrls: ['./discover.page.scss'],
})
export class DiscoverPage implements OnInit, OnDestroy {

  loadedCategories: Security[];
  isLoading = false;
  private categoriesSub: Subscription;
  isAdmin: boolean;

  constructor(
    public categoryService: SecurityService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController,
    private authService: AuthService) 
    {}

  ngOnInit() {
    this.isAdmin = this.authService.getUserRoles.includes('gsec');
    this.categoriesSub = this.categoryService.categories.subscribe(categories => {
      this.loadedCategories = categories;
      this.menuCtrl.enable(true);
    })
  }

  onDeleteCategory(id: string, slidingEl: IonItemSliding) {
    slidingEl.close();
    this.loadingCtrl.create({ message: 'Deleting...' }).then(loadingEl => {
      loadingEl.present();
      this.categoryService.deleteSecurity(id).subscribe(() => {
        loadingEl.dismiss();
      });
    });
  }

  ionViewWillEnter() {
    this.isLoading = true;
    this.categoryService.fetchSecurities().subscribe(() => {
      this.isLoading = false;
    });
  }

  onOpenMenu() {
    this.menuCtrl.toggle();
  }

  ngOnDestroy() {
    if (this.categoriesSub) {
      this.categoriesSub.unsubscribe();
    }
  }

}
