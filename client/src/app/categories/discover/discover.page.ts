import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonItemSliding, LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Category } from 'src/app/_models/category';
import { AuthService } from 'src/app/_services/auth.service';
import { CategoryService } from 'src/app/_services/category.service';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.page.html',
  styleUrls: ['./discover.page.scss'],
})
export class DiscoverPage implements OnInit, OnDestroy {

  loadedCategories: Category[];
  isLoading = false;
  private categoriesSub: Subscription;
  isAdmin: boolean;

  constructor(
    public categoryService: CategoryService,
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
      this.categoryService.deleteCategory(id).subscribe(() => {
        loadingEl.dismiss();
      });
    });
  }

  ionViewWillEnter() {
    this.isLoading = true;
    this.categoryService.fetchCategories().subscribe(() => {
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
