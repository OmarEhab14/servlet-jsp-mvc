package com.advprog.servletecommerce.application.mappers;

import com.advprog.servletecommerce.domain.dto.RegisterRequestDto;
import com.advprog.servletecommerce.domain.entities.User;
import com.advprog.servletecommerce.domain.enums.Role;

public class UserMapper {
    public static User toEntity(RegisterRequestDto registerRequestDto, String hashedPassword) {
        return User.builder()
                .firstName(registerRequestDto.firstName())
                .lastName(registerRequestDto.lastName())
                .email(registerRequestDto.email())
                .password(hashedPassword)
                .role(Role.USER)
                .build();
    }
}
