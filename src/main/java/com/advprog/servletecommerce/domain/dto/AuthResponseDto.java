package com.advprog.servletecommerce.domain.dto;

import com.advprog.servletecommerce.domain.enums.Role;
import lombok.Builder;

@Builder
public record AuthResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role,
        String token
) {
}
