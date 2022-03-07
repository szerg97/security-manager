import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DistributorsPageRoutingModule } from './distributors-routing.module';

import { DistributorsPage } from './distributors.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DistributorsPageRoutingModule
  ],
  declarations: [DistributorsPage]
})
export class DistributorsPageModule {}
