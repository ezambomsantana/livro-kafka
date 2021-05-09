package com.santana.repository;

import com.santana.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByIdentifier(String identifier);

}
