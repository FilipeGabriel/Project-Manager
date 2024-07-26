package io.filipe.product_manager.repositories;

import io.filipe.product_manager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
