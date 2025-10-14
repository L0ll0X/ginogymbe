package com.ginogym.data.repositories;

import org.springframework.stereotype.Repository;

import com.ginogym.data.entities.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    
}
