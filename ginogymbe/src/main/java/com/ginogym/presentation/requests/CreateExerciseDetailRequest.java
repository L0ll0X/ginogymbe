package com.ginogym.presentation.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseDetailRequest {
    @NotNull(message = "L'ID dell'esercizio non può essere nullo")
    private Long id;
    private Long serie;
    private Long ripetizioni;
    private Long recupero;
    private Long peso;
    private Long idEsercizio;    
    private Long idWorkoutPlan;

}
