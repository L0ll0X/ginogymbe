package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.WorkoutPlan;

@Component
public class WorkoutPlanMapper {

    private final ModelMapper mapper;

    public WorkoutPlanMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public WorkoutPlanDTO toDTO(WorkoutPlan workoutPlan) {
    // Mapper automatico per id, startDate, endDate
    return mapper.map(workoutPlan, WorkoutPlanDTO.class);
    }


   
    public WorkoutPlan toEntity(WorkoutPlanDTO dto) {
        return mapper.map(dto, WorkoutPlan.class);
    }

    
    public void updateWorkoutPlanFromDTO(WorkoutPlanDTO dto, WorkoutPlan workoutPlan) {
    if (dto.getStartDate() != null) {
        workoutPlan.setStartDate(dto.getStartDate());
    }
    if (dto.getEndDate() != null) {
        workoutPlan.setEndDate(dto.getEndDate());
    }
}

}  
 