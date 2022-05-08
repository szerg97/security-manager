import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Transaction } from '../_models/transaction';
import { AuthService } from '../_services/auth.service';
import { TransactionService } from '../_services/transaction.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.page.html',
  styleUrls: ['./transactions.page.scss'],
})
export class TransactionsPage implements OnInit, OnDestroy {

  searchTerm: string;

  loadedTransactions: Transaction[];
  isLoading = false;
  private transactionsSub: Subscription;

  constructor(
    public transactionService: TransactionService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController,
    ) 
    {}

  ngOnInit() {
    this.transactionsSub = this.transactionService.securities.subscribe(res => {
      this.loadedTransactions = res;
      this.menuCtrl.enable(true);
    });
    
    this.isLoading = true;
    this.transactionService.fetchTransactions().subscribe(() => {
      this.isLoading = false;
    });
  }

  ionViewWillEnter() {
    
  }

  ngOnDestroy() {
    if (this.transactionsSub) {
      this.transactionsSub.unsubscribe();
    }
  }

}
