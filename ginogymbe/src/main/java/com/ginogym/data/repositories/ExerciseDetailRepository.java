package com.ginogym.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ginogym.data.entities.ExerciseDetail;

public interface ExerciseDetailRepository extends JpaRepository<ExerciseDetail, Long> {

     Page<ExerciseDetail>findByExercise_Id(Long exerciseId,Pageable pageable);

     Page<ExerciseDetail>findByDayOfWeek_Id(Long dayOfWeekId,Pageable pageable);
     
}
