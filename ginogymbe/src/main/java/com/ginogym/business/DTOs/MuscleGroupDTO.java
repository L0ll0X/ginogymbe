package com.ginogym.business.DTOs;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroupDTO {
    private Long id;
    private String name;
}