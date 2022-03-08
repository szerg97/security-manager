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

  isGeneral = false;
  isInterests = false;
  isYields = false;

  constructor(
    private service: SecurityService,
    private activatedRoute: ActivatedRoute,
    public modalController: ModalController
    ) {
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

  async onGeneral(){
    this.isInterests = false;
    this.isYields = false;
    this.isGeneral = true;
    return await this.presentModal();
  }

  async onInterests(){
    this.isInterests = true;
    this.isYields = false;
    this.isGeneral = false;
    return await this.presentModal();
  }

  async onYields(){
    this.isInterests = false;
    this.isYields = true;
    this.isGeneral = false;
    return await this.presentModal();
  }

  async presentModal() {
    const modal = this.modalController.create({
      component: ModalPageComponent,
      componentProps: {
        'isGeneral': this.isGeneral,
        'isInterests': this.isInterests,
        'isYields': this.isYields,
        'security': this.security
      }
    });
    return (await modal).present();
  }

}
