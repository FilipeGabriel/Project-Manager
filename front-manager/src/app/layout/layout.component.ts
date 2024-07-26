import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  logedUser: string;

  constructor( private authService: AuthService ) { }

  ngOnInit(): void {
    this.logedUser = this.authService.getUserAuthenticated();
  }

}
