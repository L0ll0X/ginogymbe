package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.WorkoutPlan;
import com.ginogym.presentation.requests.CreateWorkoutPlanRequest;
import com.ginogym.presentation.requests.ModifyWorkoutPlanRequest;

@Component
public class WorkoutPlanMapper {

    private final ModelMapper mapper;

    public WorkoutPlanMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public WorkoutPlanDTO toDTO(WorkoutPlan workoutPlan) {
        WorkoutPlanDTO dto = mapper.map(workoutPlan, WorkoutPlanDTO.class);
        if (workoutPlan.getStartDate() != null) {
            dto.setStartDate(workoutPlan.getStartDate());
        }
        if (workoutPlan.getEndDate() != null) {
            dto.setEndDate(workoutPlan.getEndDate());
        }
          return dto;
    }

   
    public WorkoutPlan toEntity(WorkoutPlanDTO dto) {
        return mapper.map(dto, WorkoutPlan.class);
    }

    public  WorkoutPlan requestToEntity(CreateWorkoutPlanRequest request) {
        WorkoutPlan w = mapper.map(request, WorkoutPlan.class);
        //popolare le associazioni => esercizio id e dettaglio esercizo 
        w.setId(null);
        return w;
    }

 public void requestUpdateWorkoutPlanFromDTO(ModifyWorkoutPlanRequest request, WorkoutPlan workoutPlan) {
        if (request.getStartDate() != null) {
        workoutPlan.setStartDate(request.getStartDate());
    }
       if (request.getEndDate() != null) {
        workoutPlan.setEndDate(request.getEndDate());
    }
    }   

}  
 