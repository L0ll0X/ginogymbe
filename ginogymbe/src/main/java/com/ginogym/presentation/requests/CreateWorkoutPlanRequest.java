package com.ginogym.presentation.requests;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkoutPlanRequest {
    
    private String startDate; 
    private String endDate;
    private Long userId;
    private List<CreateExerciseDetailRequest> exerciseDetails= new ArrayList<>();;

}
