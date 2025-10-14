package com.ginogym.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.UserDTO;
import com.ginogym.data.entities.User;

@Component
public class UserMapper {

    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = mapper.map(user, UserDTO.class);
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                    .map(r -> r.getName())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public User toEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }

    public void updateUserFromDTO(UserDTO dto, User user) {
    if (dto.getUsername() != null) user.setUsername(dto.getUsername());
    if (dto.getEmail() != null) user.setEmail(dto.getEmail());
    if (dto.getRoles() != null) {
        // gestire la conversione Set<String> -> Set<Role>
    }
}
}
