package com.ginogym.business.DTOs;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlanDTO {

    private Long id;
<<<<<<< HEAD
    private String startDate;
    private String endDate;
    
=======
    private String startDate = "";
    private String endDate = "";
    // private UserDTO user;
    private List<ExerciseDetailDTO> exerciseDetails;
>>>>>>> develop_giulia
}
