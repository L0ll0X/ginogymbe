package com.ginogym.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ginogym.business.DTOs.ExerciseDetailDTO;


public interface ExerciseDetailService {

Page<ExerciseDetailDTO> getAllExerciseDetails(Pageable pageable);

Page<ExerciseDetailDTO> getExerciseDetailByExercise(Long exerciseId, Pageable pageable);

Page<ExerciseDetailDTO> getExerciseDetailByDayOfWeek(Long dayOfWeekId, Pageable pageable);

Optional<ExerciseDetailDTO> getExerciseDetailById(Long id);

ExerciseDetailDTO createExerciseDetail(ExerciseDetailDTO exerciseDetailDTO);

ExerciseDetailDTO updateExerciseDetail(Long id, ExerciseDetailDTO exerciseDetailDTO);

void deleteExerciseDetail(Long id);

}
