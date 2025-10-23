package com.ginogym.presentation.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   
@AllArgsConstructor
@NoArgsConstructor
public class ModifyWorkoutPlanRequest extends CreateWorkoutPlanRequest {
    
    private Long id;

}
