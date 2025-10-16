package com.ginogym.presentation.requests;

import lombok.Data;

@Data
public class CreateExerciseRequest {

    private String name;
    private Long muscleGroupId;
    private Long machineId;

   

}

