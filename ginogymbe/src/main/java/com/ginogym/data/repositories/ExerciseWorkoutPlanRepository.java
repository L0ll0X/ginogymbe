package com.ginogym.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.ExerciseWorkoutPlan;
import com.ginogym.data.entities.ExerciseWorkoutPlanId;

@Repository
public interface ExerciseWorkoutPlanRepository extends JpaRepository<ExerciseWorkoutPlan, ExerciseWorkoutPlanId> {

    // Trova tutte le relazioni per un determinato piano
    //List<ExerciseWorkoutPlan> findByExerciseWorkoutPlan_Id(Long workoutPlanId);

    // Oppure direttamente gli esercizi (più utile)
    @Query("SELECT ewp.exercise FROM ExerciseWorkoutPlan ewp WHERE ewp.workoutPlan.id = :workoutPlanId")
    List<Exercise> findExercisesByWorkoutPlanId(@Param("workoutPlanId") Long workoutPlanId);

}
