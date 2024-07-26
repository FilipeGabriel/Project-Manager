import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductsService } from 'src/app/products.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css']
})
export class ProductsFormComponent implements OnInit {

  product: Product;
  success: boolean = false;
  notSuccess: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: ProductsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.product = new Product();
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe( urlParams => {
      this.id = urlParams['id'];
      if(this.id){
        this.service.getProductById(this.id).subscribe(
          response => this.product = response,
          errorResponse => this.product = new Product()
        )
      }
    })
  }

  onSubmit(){
    if(this.id){
      this.service.updateProduct(this.product).subscribe( response => {
        this.notSuccess = false;
        this.success = true;
        this.errors = [];
        this.backToList();
      }, errorResponse => {
        this.success = false;
        this.notSuccess = true;
        this.errors = [errorResponse.error.error];
      })

    }else{

      this.service.insertProduct(this.product).subscribe( response => {
        this.notSuccess = false;
        this.success = true;
        this.errors = [];
        this.product = response;
        this.backToList();
      }, errorResponse => {
          this.success = false;
          this.notSuccess = true;
          this.errors = [errorResponse.error.error];
         })

    }
  }

  backToList(){
    this.router.navigate(['/products/list'])
  }

}
