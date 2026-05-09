package com.advprog.servletecommerce.domain.dto;
import lombok.Builder;

@Builder
public record ProductDetailsDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer stockQuantity
) {
}
