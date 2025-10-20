package com.ginogym.data.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ginogym.data.entities.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

    Optional<Exercise> findByName(String name);

    // Page<Exercise> findByMuscleGroup_Name(String muscleGroupName, Pageable pageable);

    // Page<Exercise> findByMachine_Name(String machineName, Pageable pageable);


    @Query("SELECT e FROM Exercise e JOIN FETCH e.muscleGroup JOIN FETCH e.machine")
    Page<Exercise> findAllWithRelations(Pageable pageable);

    // 2. Per getExerciseByMuscleGroup (JOIN FETCH)
    @Query("SELECT e FROM Exercise e JOIN FETCH e.muscleGroup mg JOIN FETCH e.machine WHERE mg.name = :name")
    Page<Exercise> findByMuscleGroupNameWithRelations(@Param("name") String muscleGroupName, Pageable pageable);

    // 3. Per getExerciseByMachine (JOIN FETCH)
    @Query("SELECT e FROM Exercise e JOIN FETCH e.machine m JOIN FETCH e.muscleGroup WHERE m.name = :name")
    Page<Exercise> findByMachineNameWithRelations(@Param("name") String machineName, Pageable pageable);
}
