package com.ginogym.data.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ExerciseWorkoutPlanId implements Serializable {

    private Long exerciseId;
    private Long workoutPlanId;

}
