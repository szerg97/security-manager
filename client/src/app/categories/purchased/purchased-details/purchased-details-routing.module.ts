import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PurchasedDetailsPage } from './purchased-details.page';

const routes: Routes = [
  {
    path: '',
    component: PurchasedDetailsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PurchasedDetailsPageRoutingModule {}
