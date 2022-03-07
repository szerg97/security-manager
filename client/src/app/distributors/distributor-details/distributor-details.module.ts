import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DistributorDetailsPageRoutingModule } from './distributor-details-routing.module';

import { DistributorDetailsPage } from './distributor-details.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DistributorDetailsPageRoutingModule
  ],
  declarations: [DistributorDetailsPage]
})
export class DistributorDetailsPageModule {}
