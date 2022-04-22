import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuController, ModalController, NavController } from '@ionic/angular';
import { Security } from 'src/app/_models/security';
import { Transaction } from 'src/app/_models/transaction';
import { SecurityService } from 'src/app/_services/security.service';
import { TransactionService } from 'src/app/_services/transaction.service';

@Component({
  selector: 'app-purchased-details',
  templateUrl: './purchased-details.page.html',
  styleUrls: ['./purchased-details.page.scss'],
})
export class PurchasedDetailsPage implements OnInit {

  transaction: Transaction;
  security: Security;

  constructor(
    private transactionService: TransactionService,
    private securityService: SecurityService,
    private activatedRoute: ActivatedRoute,
    public modalController: ModalController
    ) {
    }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const id = paramMap.get('id');
      this.getTransaction(id);
    });
  }

  getTransaction(id: string){
    this.transactionService.getTransaction(id).subscribe(response => {
      this.transaction = response;
      console.log(response);
      this.getSecurity(this.transaction.securityId);
    }, error => {
      console.log(error);
    });
  }

  getSecurity(id: string){
    this.securityService.getSecurity(id).subscribe(response => {
      this.security = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
  }
}
