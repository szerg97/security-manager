import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CategoriesPage } from './categories.page';

const routes: Routes = [
  {
    path: '',
    component: CategoriesPage,
    children: [
      {
        path: 'discover',
        loadChildren: () => import('./discover/discover.module').then( m => m.DiscoverPageModule)
      },
      {
        path: 'purchased',
        loadChildren: () => import('./purchased/purchased.module').then( m => m.PurchasedPageModule)
      },
      {
        path: 'statistics',
        loadChildren: () => import('./statistics/statistics.module').then( m => m.StatisticsPageModule)
      },
      {
        path: 'new-category',
        loadChildren: () => import('./new-category/new-category.module').then( m => m.NewCategoryPageModule)
      },
      {
        path: 'edit-category/:id',
        loadChildren: () => import('./edit-category/edit-category.module').then( m => m.EditCategoryPageModule)
      },
    ]
  },
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CategoriesPageRoutingModule {}
