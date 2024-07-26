import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductsFormComponent } from './products-form/products-form.component';
import { ProductsListComponent } from './products-list/products-list.component';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../auth.guard';


const routes: Routes = [
  { path: 'products', component: LayoutComponent, canActivate: [AuthGuard] , children: [
    { path: 'form', component: ProductsFormComponent },
    { path: 'form/:id', component: ProductsFormComponent },
    { path: 'list', component: ProductsListComponent },
    { path: '', redirectTo: '/products/list', pathMatch: 'full' }
  ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
