package io.filipe.product_manager.services;

import io.filipe.product_manager.controllers.dto.ProductDTO;
import io.filipe.product_manager.entities.Product;
import io.filipe.product_manager.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//Get

    public List<Product> findAll(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

//Post

    public Product insert(ProductDTO productDTO){
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return product;
    }

//Delete

    public void delete(Long id){
        productRepository.deleteById(id);
    }

//Put

    public Product update(Long id, Product newProduct){
        Product oldProduct = productRepository.findById(id).orElseThrow();
        updateProduct(oldProduct, newProduct);
        return productRepository.save(oldProduct);
    }

    public void updateProduct(Product oldProduct, Product newProduct){
        oldProduct.setName(newProduct.getName());
        oldProduct.setQuantity(newProduct.getQuantity());
        oldProduct.setPrice(newProduct.getPrice());
    }

}
