import { Component, Input} from '@angular/core';
import { MenuController, ModalController } from '@ionic/angular';
import { Security } from 'src/app/_models/security';

@Component({
  selector: 'app-modal-page',
  templateUrl: './modal-page.component.html',
  styleUrls: ['./modal-page.component.scss'],
})
export class ModalPageComponent {
  // Data passed in by componentProps
  @Input() security: Security;

  constructor(public modalController: ModalController,
    private menuCtrl: MenuController) { 
      this.menuCtrl.enable(false, 'm1');
    }

  dismiss() {
    this.modalController.dismiss({
      'dismissed': true
    });
  }
}
