package com.ginogym.business;

import com.ginogym.business.DTOs.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {

    Page<RoleDTO> getAllRoles(Pageable pageable);

    Optional<RoleDTO> getRoleById(Long id);

    Optional<RoleDTO> getRoleByName(String name);

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO updateRole(Long id, RoleDTO roleDTO);

    void deleteRole(Long id);
}