package com.ginogym.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.ExerciseDetail;
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

        if (workoutPlan.getExerciseDetails() != null) {
            dto.setExerciseDetails(mapExerciseDetails(workoutPlan.getExerciseDetails()));
        }
        return dto;
    }

private List<ExerciseDetailDTO> mapExerciseDetails(Set<ExerciseDetail> details) {
    return details.stream()
            .map(detail -> mapper.map(detail, ExerciseDetailDTO.class))
            .collect(Collectors.toList());
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
 