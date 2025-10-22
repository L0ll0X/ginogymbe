package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.business.DTOs.MuscleGroupDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.repositories.MuscleGroupRepository;
import com.ginogym.presentation.requests.CreateExerciseRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;
import com.ginogym.presentation.requests.ModifyMachineRequest;

@Component
public class ExerciseMapper {

    private final ModelMapper mapper;
    
    public ExerciseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public ExerciseDTO toDTO(Exercise exercise) {
        ExerciseDTO dto = mapper.map(exercise, ExerciseDTO.class);
        if (exercise.getName() != null) {
            dto.setName(exercise.getName());
        }
        if (exercise.getMuscleGroup() != null) {
            dto.setMuscleGroup(mapper.map(exercise.getMuscleGroup(), MuscleGroupDTO.class));
        }
         if (exercise.getMachine() != null) {
            dto.setMachine(mapper.map(exercise.getMachine(), MachineDTO.class));
        }
          return dto;
    }
    
    public Exercise toEntity(ExerciseDTO dto) {
        return mapper.map(dto, Exercise.class);
    }
    
    public Exercise requestToEntity(CreateExerciseRequest request) {
        Exercise e = mapper.map(request, Exercise.class);
        e.setId(null);
        return e;
    }
    
    public void updateExerciseFromDTO(ExerciseDTO dto, Exercise exercise) {
        if (dto.getName() != null) exercise.setName(dto.getName());
    }

    public void requestUpdateExerciseFromDTO(ModifyExerciseRequest request, Exercise exercise, MuscleGroup muscleGroup, Machine machine) {
        if (request.getName() != null) {
        exercise.setName(request.getName());
    }
        if (muscleGroup != null) { 
            exercise.setMuscleGroup(muscleGroup);
        }
        if (machine != null) { 
            exercise.setMachine(machine);
        }
    }   
 }
    


