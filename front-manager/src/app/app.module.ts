import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { ProductsModule } from './products/products.module';
import { ProductsService } from './products.service';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { AuthService } from './auth.service';
import { TokenInterceptor } from './token.interceptor';
import { AuthGuard } from './auth.guard';

const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: '', component: LayoutComponent, children: [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: '/home', pathMatch: 'full' }
  ] }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    TemplateModule,
    RouterModule.forRoot(routes),
    ProductsModule
  ],
  providers: [
    ProductsService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
