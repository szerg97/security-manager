import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PurchasedPageRoutingModule } from './purchased-routing.module';

import { PurchasedPage } from './purchased.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PurchasedPageRoutingModule
  ],
  declarations: [PurchasedPage]
})
export class PurchasedPageModule {}
