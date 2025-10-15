package com.ginogym.business.impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ginogym.business.UserService;
import com.ginogym.business.DTOs.UserDTO;
import com.ginogym.data.entities.User;
import com.ginogym.data.repositories.UserRepository;
import com.ginogym.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserServiceImpl implements UserService { 
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toDTO);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user =mapper.toEntity(userDTO);
        User saved = userRepository.save(user);
        return mapper.toDTO(saved);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        mapper.updateUserFromDTO(userDTO, existing);
        return mapper.toDTO(userRepository.save(existing));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public List<UserDTO> getUsersByRole(String roleName) {
    List<User> users = userRepository.findDistinctByRoles_Name(roleName);
    return users.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
}


   
}
