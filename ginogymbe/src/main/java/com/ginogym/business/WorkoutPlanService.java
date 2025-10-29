package com.ginogym.business;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.presentation.requests.CreateWorkoutPlanRequest;
import com.ginogym.presentation.requests.ModifyWorkoutPlanRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanService {

    Page<WorkoutPlanDTO> getAllWorkoutPlans(Pageable pageable);

    List<Exercise> getExercisesByWorkoutPlan(Long workoutPlanId);

    Optional<WorkoutPlanDTO> getWorkoutPlanById(Long id);

    // Page<WorkoutPlanDTO> getWorkoutPlansByUserId(Long userId, Pageable pageable);

    WorkoutPlanDTO createWorkoutPlan(CreateWorkoutPlanRequest workoutPlanDTO);

    WorkoutPlanDTO updateWorkoutPlan(Long id, ModifyWorkoutPlanRequest workoutPlanDTO);

    void deleteWorkoutPlan(Long id);
}

