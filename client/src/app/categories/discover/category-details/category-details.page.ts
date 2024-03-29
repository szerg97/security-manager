import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DomController, LoadingController, MenuController, NavController } from '@ionic/angular';
import { Security } from 'src/app/_models/security';
import { Transaction } from 'src/app/_models/transaction';
import { AuthService } from 'src/app/_services/auth.service';
import { SecurityService } from 'src/app/_services/security.service';
import { TransactionService } from 'src/app/_services/transaction.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.page.html',
  styleUrls: ['./category-details.page.scss'],
})
export class CategoryDetailsPage implements OnInit {

  @ViewChild("denominationCtrl") denom;
  
  category: Security;

  isAdmin: boolean;

  denomVal: number = 0;

  exchangeRate: number = 0;

  constructor(
    private service: SecurityService,
    private transactionService: TransactionService,
    private navCtrl: NavController,
    private activatedRoute: ActivatedRoute,
    private loadingCtrl: LoadingController,
    private authServcie: AuthService) {
    }

  ngOnInit() {
    this.isAdmin = this.authServcie.getUserRoles.includes('gsec');
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const categoryId = paramMap.get('id');
      this.getCategory(categoryId);
    });
  }

  onChange(){
    this.denomVal = this.denom.value;
  }

  getCategory(id: string){
    this.service.getSecurity(id).subscribe(response => {
      this.category = response;
      console.log(this.category);
      this.exchangeRate = this.category.exchangeRate as unknown as number;
    }, error => {
      console.log(error);
    });
  }

  onPurchase(form: NgForm){
    if (!form.valid){
      return;
    }

    this.loadingCtrl.create({
      message: 'Transaction in progress...'
    })
    .then(loadingEl => {
      loadingEl.present();
      const denomination = form.value.denomination;
      const securityId = this.category.id;
      const model = {denomination, securityId}
      console.log(model);

      this.transactionService
        .addTransaction(
          model as Transaction
        )
        .subscribe(() => {
          loadingEl.dismiss();
          form.reset();
          this.navCtrl.navigateBack('/categories/discover');
        });
    });

    
  }
}
