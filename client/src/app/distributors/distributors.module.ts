import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DistributorsPageRoutingModule } from './distributors-routing.module';

import { DistributorsPage } from './distributors.page';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DistributorsPageRoutingModule,
    Ng2SearchPipeModule
  ],
  declarations: [DistributorsPage]
})
export class DistributorsPageModule {}
