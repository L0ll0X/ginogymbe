package com.ginogym.business;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WorkoutPlanService {

    Page<WorkoutPlanDTO> getAllWorkoutPlans(Pageable pageable);

    Optional<WorkoutPlanDTO> getWorkoutPlanById(Long id);

    Page<WorkoutPlanDTO> getWorkoutPlansByUserId(Long userId, Pageable pageable);

    WorkoutPlanDTO createWorkoutPlan(WorkoutPlanDTO dto);

    WorkoutPlanDTO updateWorkoutPlan(Long id, WorkoutPlanDTO dto);

    void deleteWorkoutPlan(Long id);
}

