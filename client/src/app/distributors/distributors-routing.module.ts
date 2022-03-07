import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DistributorsPage } from './distributors.page';

const routes: Routes = [
  {
    path: '',
    component: DistributorsPage,
  },
  {
    path: 'new-distributor',
    loadChildren: () => import('./new-distributor/new-distributor.module').then( m => m.NewDistributorPageModule)
  },
  {
    path: ':id',
    loadChildren: () => import('./distributor-details/distributor-details.module').then( m => m.DistributorDetailsPageModule)
  },
  {
    path: 'edit-distributor/:id',
    loadChildren: () => import('./edit-distributor/edit-distributor.module').then( m => m.EditDistributorPageModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DistributorsPageRoutingModule {}
