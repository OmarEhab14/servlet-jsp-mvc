
package com.advprog.servletecommerce.domain.dto;

import lombok.Builder;

@Builder
public record ReviewRequestDto(
        Long userId,
        Long productId,
        int rating,
        String comment
) {
}