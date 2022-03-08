import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { PurchasedDetailsPageRoutingModule } from './purchased-details-routing.module';

import { PurchasedDetailsPage } from './purchased-details.page';
import { ModalPageComponent } from './modal-page/modal-page.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PurchasedDetailsPageRoutingModule
  ],
  declarations: [PurchasedDetailsPage, ModalPageComponent]
})
export class PurchasedDetailsPageModule {}
