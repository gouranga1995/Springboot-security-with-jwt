package com.dev.spring.security.jwt.service;

import com.dev.spring.security.jwt.entity.User;

import java.util.Optional;

public interface IUserService {
    Integer saveUser(User user);

    Optional<User> findByUsername(String username);
}
