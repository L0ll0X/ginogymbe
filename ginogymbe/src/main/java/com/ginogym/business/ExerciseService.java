package com.ginogym.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.presentation.request.CreateExerciseRequest;
import com.ginogym.presentation.request.ModifyExerciseRequest;

public interface ExerciseService {

    Page<ExerciseDTO> getAllExercises (Pageable pageable);

    Optional<ExerciseDTO> getExerciseById (Long id);

    Optional<ExerciseDTO> getExerciseByName(String name);

    Page<ExerciseDTO> getExerciseByMuscleGroup (String muscleGroupName, Pageable pageable);

    Page<ExerciseDTO> getExerciseByMachine (String machineName, Pageable pageable);

    ExerciseDTO createExercise (CreateExerciseRequest exerciseDTO);

    ExerciseDTO updateExercise (Long id, ModifyExerciseRequest exerciseDTO);

    void deleteExercise (Long id);

}
