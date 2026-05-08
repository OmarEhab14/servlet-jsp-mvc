package com.advprog.servletecommerce.domain.dto;

import lombok.Builder;

@Builder
public record LoginRequestDto(
        String email,
        String password
) {
}
