package com.ginogym.data.repositories;

import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    Optional<MuscleGroup> findByName(String name);
     Optional<Machine>findById(Long id);

}