package com.ginogym.presentation.requests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkoutPlanRequest {
    
    private String startDate; 
    private String endDate;
    // private Long userId;
    private List<CreateExerciseDetailRequest> exerciseDetails= new ArrayList<>();

}
