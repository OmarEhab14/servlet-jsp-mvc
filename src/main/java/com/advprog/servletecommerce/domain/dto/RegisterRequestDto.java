package com.advprog.servletecommerce.domain.dto;

import lombok.Builder;

@Builder
public record RegisterRequestDto(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
