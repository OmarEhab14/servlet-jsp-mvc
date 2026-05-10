package com.advprog.servletecommerce.application.service;

import java.util.List;

import com.advprog.servletecommerce.domain.dto.ProductDetailsDto;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.entities.Product;

public interface ProductService {
    Product createProduct(Product product);

    ProductDetailsDto getProduct(Long id);

    List<ProductDto> getAllProducts();

    void deleteProduct(Long id);
}
