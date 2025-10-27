package com.ginogym.business.DTOs;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String cellulare;
    private Set<String> roles = new HashSet<>();;
}

