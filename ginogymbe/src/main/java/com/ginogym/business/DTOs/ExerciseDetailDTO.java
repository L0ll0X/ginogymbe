package com.ginogym.business.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDetailDTO {
    private Long id;
    private Long serie;
    private Long ripetizioni;
    private Long recupero;
    private Long peso;
    private ExerciseDTO exercise;    
    private WorkoutPlanDTO workoutPlan;  
    private String dayOfWeekId;

}
