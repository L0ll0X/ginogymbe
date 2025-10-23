package com.ginogym.business.DTOs;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlanDTO {

    private Long id;
    private String startDate;
    private String endDate;

}
