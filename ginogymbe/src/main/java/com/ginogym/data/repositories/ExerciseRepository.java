package com.ginogym.data.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ginogym.data.entities.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

    Optional<Exercise> findByName(String name);

    Page<Exercise> findByMuscleGroup_Name(String muscleGroupName, Pageable pageable);

    Page<Exercise> findByMachine_Name(String machineName, Pageable pageable);

}
