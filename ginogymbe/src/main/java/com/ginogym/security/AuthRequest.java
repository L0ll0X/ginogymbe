package com.ginogym.security;

import lombok.Data;

@Data
public class AuthRequest {

    private String usernameOrEmail;
    private String password;

}