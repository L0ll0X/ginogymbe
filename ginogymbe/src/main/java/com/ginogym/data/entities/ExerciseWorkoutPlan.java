package com.ginogym.data.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercise_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseWorkoutPlan {

    @EmbeddedId
    private ExerciseWorkoutPlanId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exerciseId")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("workoutPlanId")
    private WorkoutPlan workoutPlan;

}
