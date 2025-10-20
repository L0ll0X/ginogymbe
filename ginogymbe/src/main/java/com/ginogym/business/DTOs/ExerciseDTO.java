package com.ginogym.business.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDTO {

    private Long id;
    private String name;
    private MuscleGroupDTO muscleGroup;
    private MachineDTO machine;

}
