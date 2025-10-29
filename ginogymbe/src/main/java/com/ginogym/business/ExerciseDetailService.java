package com.ginogym.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.presentation.requests.CreateExerciseDetailRequest;
import com.ginogym.presentation.requests.ModifyExerciseDetailRequest;

public interface ExerciseDetailService {

Page<ExerciseDetailDTO> getAllExerciseDetails(Pageable pageable);

Page<ExerciseDetailDTO> getExerciseDetailByExercise(Long exerciseId, Pageable pageable);

Page<ExerciseDetailDTO> getExerciseDetailByDayOfWeek(Long dayOfWeekId, Pageable pageable);

Optional<ExerciseDetailDTO> getExerciseDetailById(Long id);

Page<ExerciseDetailDTO> getExerciseDetailByWorkoutPlan(Long workoutPlanId, Pageable pageable);

ExerciseDetailDTO createExerciseDetail(CreateExerciseDetailRequest exerciseDetailDTO);

ExerciseDetailDTO updateExerciseDetail(Long id, ModifyExerciseDetailRequest exerciseDetailDTO);

void deleteExerciseDetail(Long id);

}
