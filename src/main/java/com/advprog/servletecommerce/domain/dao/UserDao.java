package com.advprog.servletecommerce.domain.dao;

import com.advprog.servletecommerce.domain.entities.User;

import java.util.Optional;

public interface UserDao {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean deleteById(Long id);
}
