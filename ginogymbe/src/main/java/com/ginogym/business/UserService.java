package com.ginogym.business;

import com.ginogym.business.DTOs.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<UserDTO> getAllUsers(Pageable pageable);

    Optional<UserDTO> getUserById(Long id);

    Optional<UserDTO> getUserByEmail(String email);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}