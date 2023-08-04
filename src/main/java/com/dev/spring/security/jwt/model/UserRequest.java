package com.dev.spring.security.jwt.model;

import lombok.Data;

@Data
public class UserRequest {
    public String username;
    public String password;
}
