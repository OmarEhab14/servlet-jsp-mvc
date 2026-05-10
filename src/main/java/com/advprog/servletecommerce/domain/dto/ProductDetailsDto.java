package com.advprog.servletecommerce.domain.dto;
import com.advprog.servletecommerce.domain.entities.Review;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDetailsDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer stockQuantity,
        String imageUrl,
        List<Review>reviews
) {
}
