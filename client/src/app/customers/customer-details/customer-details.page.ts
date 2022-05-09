import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController, NavController, SegmentChangeEventDetail } from '@ionic/angular';
import { Customer } from 'src/app/_models/customer';
import { CustomerService } from 'src/app/_services/customer.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.page.html',
  styleUrls: ['./customer-details.page.scss'],
})
export class CustomerDetailsPage implements OnInit {

  customer: Customer;

  isGeneral: boolean = true;

  constructor(
    private service: CustomerService,
    private navCtrl: NavController,
    private activatedRoute: ActivatedRoute,
    private loadingCtrl: LoadingController) {
    }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const customerId = paramMap.get('id');
      this.getCustomer(customerId);
    });
  }

  getCustomer(id: string){
    this.service.getCustomer(id).subscribe(response => {
      this.customer = response;
      console.log(this.customer);
    }, error => {
      console.log(error);
    });
  }

  onFilter(event: Event){
    if((event as CustomEvent<SegmentChangeEventDetail>).detail.value == 'general'){
      this.isGeneral = true;
    }
    else{
      this.isGeneral = false;
    }
  }
}
