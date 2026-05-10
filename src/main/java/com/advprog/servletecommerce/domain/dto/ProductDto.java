package com.advprog.servletecommerce.domain.dto;
import com.advprog.servletecommerce.domain.entities.Review;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDto(
        Long id,
        String name,
        Double price,
        Integer stockQuantity
//        List<Review> reviews
) {
}
