package com.ginogym.data.repositories;

import com.ginogym.data.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    List<Machine> findByMuscleGroup_Name(String muscleGroupName);

    List<Machine> findByBrandContainingIgnoreCase(String brand);
}