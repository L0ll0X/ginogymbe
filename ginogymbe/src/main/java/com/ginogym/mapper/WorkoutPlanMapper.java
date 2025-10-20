package com.ginogym.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.ExerciseDetail;
import com.ginogym.data.entities.WorkoutPlan;

@Component
public class WorkoutPlanMapper {

    private final ModelMapper mapper;

    public WorkoutPlanMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }




    public WorkoutPlanDTO toDTO(WorkoutPlan workoutPlan) {
        WorkoutPlanDTO dto = mapper.map(workoutPlan, WorkoutPlanDTO.class);
        if (workoutPlan.getExerciseDetails() != null) {
            dto.setExerciseDetails(workoutPlan.getExerciseDetails().stream()
                    .map(ed -> ed.getId()) 
                    .collect(Collectors.toList()));
        }
        return dto;
    }

   
    public WorkoutPlan toEntity(WorkoutPlanDTO dto) {
        return mapper.map(dto, WorkoutPlan.class);
    }

    
    public void updateWorkoutPlanFromDTO(WorkoutPlanDTO dto, WorkoutPlan workoutPlan) {
        if (dto.getStartDate() != null) workoutPlan.setStartDate(dto.getStartDate());
        if (dto.getStartDate() != null) workoutPlan.setEndDate(dto.getEndDate());
        if (dto.getExerciseDetails() != null) {
                workoutPlan.getExerciseDetails().clear();
            for (Long edId : dto.getExerciseDetails()) {
                ExerciseDetail ed = new ExerciseDetail();
                ed.setId(edId);
                ed.setWorkoutPlan(workoutPlan); 
                workoutPlan.getExerciseDetails().add(ed);
            }
        }
    }
}  
 