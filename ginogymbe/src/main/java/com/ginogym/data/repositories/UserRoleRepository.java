package com.ginogym.data.repositories;

import com.ginogym.data.entities.UserRole;
import com.ginogym.data.entities.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId>{

    //nominali
    List<UserRole> findByUser_Id(Long userId);
    List<UserRole> findByRole_Id(Long roleId); 
}