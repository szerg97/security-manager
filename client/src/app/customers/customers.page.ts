import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonItemSliding, LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Customer } from '../_models/customer';
import { AuthService } from '../_services/auth.service';
import { CustomerService } from '../_services/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.page.html',
  styleUrls: ['./customers.page.scss'],
})
export class CustomersPage implements OnInit, OnDestroy {

  searchTerm: string;

  loadedCustomers: Customer[];
  isLoading = false;
  private customersSub: Subscription;
  isAdmin: boolean;

  constructor(
    public customerService: CustomerService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController,
    private authService: AuthService) 
    {}

  ngOnInit() {
    this.isAdmin = this.authService.getUserRoles.includes('gsec');
    this.customersSub = this.customerService.customers.subscribe(data => {
      this.loadedCustomers = data;
      this.menuCtrl.enable(true);
    })
  }

  onDeleteCustomer(id: string, slidingEl: IonItemSliding) {
    slidingEl.close();
    this.loadingCtrl.create({ message: 'Deleting...' }).then(loadingEl => {
      loadingEl.present();
      this.customerService.deleteCustomer(id).subscribe(() => {
        loadingEl.dismiss();
      });
    });
  }

  ionViewWillEnter() {
    this.isLoading = true;
    this.customerService.fetchCustomers().subscribe(() => {
      this.isLoading = false;
    });
  }

  onOpenMenu() {
    this.menuCtrl.toggle();
  }

  ngOnDestroy() {
    if (this.customersSub) {
      this.customersSub.unsubscribe();
    }
  }

}
