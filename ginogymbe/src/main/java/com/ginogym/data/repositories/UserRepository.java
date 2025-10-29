package com.ginogym.data.repositories;

import com.ginogym.data.entities.Role;
import com.ginogym.data.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); 

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findDistinctByRoles_Name(String roleName);

    Page<User> findDistinctByRoles_Name(String roleName, org.springframework.data.domain.Pageable pageable);

}