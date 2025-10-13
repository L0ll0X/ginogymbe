package com.example.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.data.GruppoMuscolareEntity;

public interface GruppoMuscolareRepository extends JpaRepository<GruppoMuscolareEntity,Long> {
@Override
Page<GruppoMuscolareEntity>findAll(Pageable pageable);

	
}
