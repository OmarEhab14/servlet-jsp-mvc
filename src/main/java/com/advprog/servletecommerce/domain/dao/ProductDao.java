package com.advprog.servletecommerce.domain.dao;

import com.advprog.servletecommerce.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<Product> findById(Long id);
    Product save(Product product);
    List<Product> findAll();
    boolean deleteById(Long id);
}
