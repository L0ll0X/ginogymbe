package com.ginogym.data.repositories;

import com.ginogym.data.entities.Machine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    Page<Machine> findByMuscleGroup_Name(String muscleGroupName,Pageable pageable);

}