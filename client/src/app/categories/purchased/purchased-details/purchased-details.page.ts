import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuController, ModalController, NavController } from '@ionic/angular';
import { Security } from 'src/app/_models/security';
import { SecurityService } from 'src/app/_services/security.service';
import { ModalPageComponent } from './modal-page/modal-page.component';

@Component({
  selector: 'app-purchased-details',
  templateUrl: './purchased-details.page.html',
  styleUrls: ['./purchased-details.page.scss'],
})
export class PurchasedDetailsPage implements OnInit {

  security: Security;

  constructor(
    private service: SecurityService,
    private navCtrl: NavController,
    private activatedRoute: ActivatedRoute,
    private menuCtrl: MenuController,
    public modalController: ModalController
    ) {
      this.menuCtrl.enable(false, 'm1');
    }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      if(!paramMap.has('id')){
        return;
      }
      const securityId = paramMap.get('id');
      this.getSecuity(securityId);
    });
  }

  getSecuity(id: string){
    this.service.getSecurity(id).subscribe(response => {
      this.security = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
  }

  async presentModal() {
    const modal = this.modalController.create({
      component: ModalPageComponent,
      componentProps: {
        'security': this.security
      }
    });
    return (await modal).present();
  }

}
