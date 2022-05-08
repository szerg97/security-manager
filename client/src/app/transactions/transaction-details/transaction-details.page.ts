import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController, NavController } from '@ionic/angular';
import { Transaction } from 'src/app/_models/transaction';
import { TransactionExtended } from 'src/app/_models/transaction-extended';
import { TransactionService } from 'src/app/_services/transaction.service';

@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.page.html',
  styleUrls: ['./transaction-details.page.scss'],
})
export class TransactionDetailsPage implements OnInit {

  transaction: TransactionExtended;

  constructor(
    private transactionService: TransactionService,
    private navCtrl: NavController,
    private activatedRoute: ActivatedRoute,
    private loadingCtrl: LoadingController
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const trId = paramMap.get('id');
      this.getTransactionExtended(trId);
    });
  }

  getTransactionExtended(id: string){
    this.transactionService.getTransactionExtended(id).subscribe(response => {
      this.transaction = response;
      console.log(this.transaction);
    }, error => {
      console.log(error);
    });
  }

}
