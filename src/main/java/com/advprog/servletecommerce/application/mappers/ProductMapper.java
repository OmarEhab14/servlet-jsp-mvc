package com.advprog.servletecommerce.application.mappers;

import java.util.List;

import com.advprog.servletecommerce.domain.dto.ProductDetailsDto;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.dto.RegisterRequestDto;
import com.advprog.servletecommerce.domain.entities.Product;
import com.advprog.servletecommerce.domain.entities.ProductDetails;
import com.advprog.servletecommerce.domain.entities.User;
import com.advprog.servletecommerce.domain.enums.Role;

public class ProductMapper {
    public static Product toEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.id())
                .name(productDto.name())
                .price(productDto.price())
                .stockQuantity(productDto.stockQuantity())
                .build();
    }
    public static ProductDto toDto(Product product){
        return ProductDto.builder()
                         .id(product.getId())
                         .name(product.getName())
                         .price(product.getPrice())
                         .stockQuantity(product.getStockQuantity())
                         .build();
    }
    public static Product toEntity(ProductDetailsDto productDetailsDto) {
        return Product.builder()
                      .id(productDetailsDto.id())
                      .name(productDetailsDto.name())
                      .price(productDetailsDto.price())
                      .description(productDetailsDto.description())
                      .stockQuantity(productDetailsDto.stockQuantity())
                      .build();
    }
    public static ProductDetailsDto toDetailsDto(ProductDetails product){
        return ProductDetailsDto.builder()
                         .id(product.getId())
                         .name(product.getName())
                         .price(product.getPrice())
                         .description(product.getDescription())
                         .stockQuantity(product.getStockQuantity())
                         .build();
    }
}
