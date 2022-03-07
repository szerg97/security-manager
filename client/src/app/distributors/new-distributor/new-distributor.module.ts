import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NewDistributorPageRoutingModule } from './new-distributor-routing.module';

import { NewDistributorPage } from './new-distributor.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NewDistributorPageRoutingModule
  ],
  declarations: [NewDistributorPage]
})
export class NewDistributorPageModule {}
