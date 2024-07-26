import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  success: boolean;
  signingUp: boolean;
  successMessage: string;
  errorMessage: string;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  onSubmit(){
    this.authService
        .tryLogin( this.username, this.password )
        .subscribe(response => {
          const access_token = JSON.stringify(response);
          localStorage.setItem('access_token', access_token)
          this.router.navigate(['/home'])
        }, responseError => {
          this.success = false;
          this.errorMessage = 'Usuário e/ou senha incorreto!'
        })
  }

  prepareSigningUp(event: any){
    event.preventDefault();
    this.signingUp = true;
  }

  cancelSigningUp(){
    this.signingUp = false;
  }

  signUp(){
    const user: User = new User();
    user.username = this.username;
    user.password = this.password;
    this.authService
        .insertUser(user)
        .subscribe(response => {
          this.successMessage = "Cadastro realizado com sucesso! Efetue o login."
          this.success = true;
          this.signingUp = false;
          this.username = "";
          this.password = "";
        }, responseError => {
          this.success = false;
          this.errorMessage = responseError.error.error;
        } )
  }

}
