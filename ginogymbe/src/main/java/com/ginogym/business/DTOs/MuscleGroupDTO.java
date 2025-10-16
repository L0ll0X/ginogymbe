package com.ginogym.business.DTOs;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroupDTO {
    private Long id;
    private String name;
}