import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewDistributorPage } from './new-distributor.page';

const routes: Routes = [
  {
    path: '',
    component: NewDistributorPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewDistributorPageRoutingModule {}
