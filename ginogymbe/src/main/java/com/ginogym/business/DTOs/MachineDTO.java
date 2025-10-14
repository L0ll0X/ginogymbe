package com.ginogym.business.DTOs;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineDTO {
    private Long id;
    private String name;
    private String description;
    private String muscleGroup;
}