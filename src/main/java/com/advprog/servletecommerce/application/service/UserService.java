package com.advprog.servletecommerce.application.service;

import com.advprog.servletecommerce.domain.dto.AuthResponseDto;
import com.advprog.servletecommerce.domain.dto.LoginRequestDto;
import com.advprog.servletecommerce.domain.dto.RegisterRequestDto;
import com.advprog.servletecommerce.domain.entities.User;

public interface UserService {
    AuthResponseDto register(RegisterRequestDto registerRequestDto);
    AuthResponseDto login(LoginRequestDto loginRequestDto);
    User getUserById(Long id);
}
