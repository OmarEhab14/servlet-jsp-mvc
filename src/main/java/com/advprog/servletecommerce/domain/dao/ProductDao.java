package com.advprog.servletecommerce.domain.dao;

import com.advprog.servletecommerce.domain.entities.Product;
import com.advprog.servletecommerce.domain.entities.ProductDetails;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<ProductDetails> findById(Long id);
    Product save(Product product);
    List<Product> findAll();
    boolean deleteById(Long id);
    boolean existsById(Long id);
}
