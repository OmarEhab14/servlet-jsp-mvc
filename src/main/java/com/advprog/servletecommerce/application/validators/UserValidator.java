package com.advprog.servletecommerce.application.validators;

import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.domain.dao.UserDao;
import com.advprog.servletecommerce.domain.dto.LoginRequestDto;
import com.advprog.servletecommerce.domain.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserValidator {
    private final UserDao userDao;

    public void validateRegister(RegisterRequestDto registerRequestDto) {
        Map<String, String> errors = new HashMap<>();
        if (registerRequestDto.firstName() == null || registerRequestDto.firstName().trim().isEmpty()) {
            errors.put("firstName", "First name is required");
        } else if (registerRequestDto.firstName().length() < 2) {
            errors.put("firstName", "First name must be at least 2 characters");
        }

        if (registerRequestDto.lastName() == null || registerRequestDto.lastName().trim().isEmpty()) {
            errors.put("lastName", "Last name is required");
        } else if (registerRequestDto.lastName().length() < 2) {
            errors.put("lastName", "Last name must be at least 2 characters");
        }

        if (registerRequestDto.email() == null || registerRequestDto.email().trim().isEmpty()) {
            errors.put("email", "Email is required");
        } else if (!registerRequestDto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.put("email", "Invalid email address");
        } else if (userDao.existsByEmail(registerRequestDto.email())) {
            errors.put("email", "Email already exists");
        }

        if (registerRequestDto.password() == null || registerRequestDto.password().trim().isEmpty()) {
            errors.put("password", "Password is required");
        } else if (!registerRequestDto.password().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {
            errors.put("password", "Password must be at least 8 characters and include uppercase, lowercase, number, and special character");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void validateLogin(LoginRequestDto loginRequestDto) {
        Map<String, String> errors = new HashMap<>();

        if (loginRequestDto.email() == null || loginRequestDto.email().trim().isEmpty()) {
            errors.put("email", "Email is required");
        }

        if (loginRequestDto.password() == null || loginRequestDto.password().trim().isEmpty()) {
            errors.put("password", "Password is required");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
