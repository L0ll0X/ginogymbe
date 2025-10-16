package com.ginogym.business.DTOs;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineDTO {
    private Long id;
    private String name;
    private String description;
   private MuscleGroupDTO muscleGroup;
}