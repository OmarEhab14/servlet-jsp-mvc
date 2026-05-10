package com.advprog.servletecommerce.domain.dto;
import lombok.Builder;

@Builder
public record ProductDto(
        Long id,
        String name,
        Double price,
        Integer stockQuantity
) {
}
