import { Component, Input} from '@angular/core';
import { MenuController, ModalController } from '@ionic/angular';
import { Transaction } from 'src/app/_models/transaction';

@Component({
  selector: 'app-modal-page',
  templateUrl: './modal-page.component.html',
  styleUrls: ['./modal-page.component.scss'],
})
export class ModalPageComponent {
  // Data passed in by componentProps
  @Input() security: Transaction;
  @Input() isGeneral: boolean;
  @Input() isInterests: boolean;
  @Input() isYields: boolean;

  constructor(public modalController: ModalController) { 
      console.log(this.isGeneral, this.isInterests, this.isYields, this.security);
    }

  dismiss() {
    this.modalController.dismiss({
      'dismissed': true
    });
  }
}
