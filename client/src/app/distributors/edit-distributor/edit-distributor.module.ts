import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EditDistributorPageRoutingModule } from './edit-distributor-routing.module';

import { EditDistributorPage } from './edit-distributor.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EditDistributorPageRoutingModule
  ],
  declarations: [EditDistributorPage]
})
export class EditDistributorPageModule {}
