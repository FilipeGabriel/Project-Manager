import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/products.service';
import { Product } from '../product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  products: Product[] = [];
  selectedProduct: Product;
  successMessage: string;
  errorMessage: string;

  constructor( private service: ProductsService, private router: Router ) {

  }

  ngOnInit(): void {
    this.service.getProducts().subscribe(response => this.products = response);
  }

  newInsert(){
    this.router.navigate(['/products/form']);
  }

  prepareDelete(product: Product){
    this.selectedProduct = product;
  }

  deleteProduct(){
    this.service.deleteProduct(this.selectedProduct).subscribe(
      response => {
        this.successMessage = 'Produto deletado com sucesso!',
        this.ngOnInit();
      },
      erroResponse => this.errorMessage = 'Ocorreu um erro ao deletar o produto'
    );
  }

}
