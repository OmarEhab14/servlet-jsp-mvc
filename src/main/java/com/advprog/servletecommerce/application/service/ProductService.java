package com.advprog.servletecommerce.application.service;

import java.util.List;

import com.advprog.servletecommerce.domain.entities.Product;

public interface ProductService {
    Product createProduct(Product product);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    void deleteProduct(Long id);
}
