import { Component, OnInit } from '@angular/core';
import { LoadingController, MenuController } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { Transaction } from 'src/app/_models/transaction';
import { AuthService } from 'src/app/_services/auth.service';
import { TransactionService } from 'src/app/_services/transaction.service';

@Component({
  selector: 'app-purchased',
  templateUrl: './purchased.page.html',
  styleUrls: ['./purchased.page.scss'],
})
export class PurchasedPage implements OnInit {

  loadedTransactions: Transaction[];
  isLoading = false;
  private transactionsSub: Subscription;

  constructor(
    private transactionService: TransactionService,
    private menuCtrl: MenuController,
    private loadingCtrl: LoadingController
    ) { }

    ngOnInit() {
      this.transactionsSub = this.transactionService.securities.subscribe(sec => {
        this.loadedTransactions = sec;
        this.menuCtrl.enable(true);
      })
    }
  
    ionViewWillEnter() {
      this.isLoading = true;
      this.transactionService.fetchTransactionsByCustomer().subscribe(() => {
        this.isLoading = false;
      });
    }
  
    onOpenMenu() {
      this.menuCtrl.toggle();
    }
  
    ngOnDestroy() {
      if (this.transactionsSub) {
        this.transactionsSub.unsubscribe();
      }
    }

}
