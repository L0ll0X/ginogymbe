package com.ginogym.presentation.requests;

import java.util.ArrayList;
import java.util.List;

import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.data.entities.ExerciseDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDetailRequest {
    private Long serie;
    private Integer ripetizioni;
    private Integer recupero;
    private Integer peso;
    private Long id;
    private Long exerciseId;
    private Long schedaId;
  
}
