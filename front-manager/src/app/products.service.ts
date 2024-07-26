import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './products/product';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  apiUrlBase: string = environment.apiUrlBase + '/api/products';

  constructor( private http: HttpClient ) {
  }

  insertProduct( product: Product ) : Observable<Product>{
    return this.http.post<Product>(this.apiUrlBase, product)
  }

  getProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(this.apiUrlBase);
  }

  getProductById( id: number ): Observable<Product>{
    return this.http.get<any>(`${this.apiUrlBase}/${id}`);
  }

  updateProduct( product: Product ): Observable<any>{
    return this.http.put<Product>(`${this.apiUrlBase}/${product.id}`, product)
  }

  deleteProduct( product: Product ): Observable<any>{
    return this.http.delete<any>(`${this.apiUrlBase}/${product.id}`)
  }

}
