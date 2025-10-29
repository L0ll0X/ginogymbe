package com.ginogym.data.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ginogym.data.entities.ExerciseDetail;

public interface ExerciseDetailRepository extends JpaRepository<ExerciseDetail, Long> {

     Optional<ExerciseDetail> findById(Long id);

     Page<ExerciseDetail> findByExercise_Id(Long exerciseId, Pageable pageable);

     Page<ExerciseDetail> findByDaysOfWeek_Id(Long dayOfWeekId, Pageable pageable);

     @Query("SELECT ed FROM ExerciseDetail ed " +
               "JOIN FETCH ed.exercise e " +
               "LEFT JOIN FETCH ed.daysOfWeek dw " +
               "WHERE ed.workoutPlans.id = :workoutPlanId")
     Page<ExerciseDetail> findByWorkoutPlansIdWithDetails(@Param("workoutPlanId") Long workoutPlanId, Pageable pageable);

     Page<ExerciseDetail> findByWorkoutPlans_Id(Long workoutPlanId, Pageable pageable);

}
