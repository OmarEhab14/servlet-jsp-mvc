package com.advprog.servletecommerce.application.mappers;

import com.advprog.servletecommerce.domain.dto.ProductDetailsDto;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.entities.Product;

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
                      .imageUrl(productDetailsDto.imageUrl())
                      .stockQuantity(productDetailsDto.stockQuantity())
                      .build();
    }
    public static ProductDetailsDto toDetailsDto(Product product){
        return ProductDetailsDto.builder()
                         .id(product.getId())
                         .name(product.getName())
                         .price(product.getPrice())
                         .description(product.getDescription())
                         .imageUrl(product.getImageUrl())
                         .stockQuantity(product.getStockQuantity())
                         .build();
    }
}
