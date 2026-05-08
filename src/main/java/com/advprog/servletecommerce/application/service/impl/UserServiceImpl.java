package com.advprog.servletecommerce.application.service.impl;

import com.advprog.servletecommerce.application.exceptions.NotFoundException;
import com.advprog.servletecommerce.application.exceptions.ValidationException;
import com.advprog.servletecommerce.application.mappers.UserMapper;
import com.advprog.servletecommerce.application.security.JwtUtil;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.application.validators.UserValidator;
import com.advprog.servletecommerce.domain.dao.UserDao;
import com.advprog.servletecommerce.domain.dto.AuthResponseDto;
import com.advprog.servletecommerce.domain.dto.LoginRequestDto;
import com.advprog.servletecommerce.domain.dto.RegisterRequestDto;
import com.advprog.servletecommerce.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;

    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        userValidator.validateRegister(registerRequestDto);

        String hashedPassword = BCrypt.hashpw(registerRequestDto.password(), BCrypt.gensalt());
        User user = UserMapper.toEntity(registerRequestDto, hashedPassword);
        User savedUser = userDao.save(user);
        String token = JwtUtil.generateToken(savedUser);
        return AuthResponseDto.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        userValidator.validateLogin(loginRequestDto);

        Optional<User> userOptional = userDao.findByEmail(loginRequestDto.email());
        if (userOptional.isEmpty() || !BCrypt.checkpw(loginRequestDto.password(), userOptional.get().getPassword())) {
            Map<String, String> errors = new HashMap<>();
            errors.put("login", "Invalid email or password");
            throw new ValidationException(errors);
        }

        User user = userOptional.get();
        String token = JwtUtil.generateToken(user);

        return AuthResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userOptional.get();
    }
}
