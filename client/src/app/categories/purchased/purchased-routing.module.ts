import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PurchasedPage } from './purchased.page';

const routes: Routes = [
  {
    path: '',
    component: PurchasedPage
  },
  {
    path: ':id',
    loadChildren: () => import('./purchased-details/purchased-details.module').then( m => m.PurchasedDetailsPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PurchasedPageRoutingModule {}
