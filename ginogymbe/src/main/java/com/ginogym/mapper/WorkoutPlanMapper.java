package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ginogym.business.DTOs.WorkoutPlanDTO;
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
        if (workoutPlan.getExerciseDetails() != null) {
        org.hibernate.Hibernate.initialize(workoutPlan.getExerciseDetails()); 
    }

      return mapper.map(workoutPlan, WorkoutPlanDTO.class);
}    

    public WorkoutPlan toEntity(WorkoutPlanDTO dto) {
        return mapper.map(dto, WorkoutPlan.class);
    }

    public  WorkoutPlan requestToEntity(CreateWorkoutPlanRequest request) {
        WorkoutPlan w = mapper.map(request, WorkoutPlan.class);
        w.setId(null);
        return w;
    }

 public void requestUpdateWorkoutPlanFromDTO(ModifyWorkoutPlanRequest request, WorkoutPlan workoutPlan) {
      mapper.map(request, workoutPlan);
    
}  
}
 
