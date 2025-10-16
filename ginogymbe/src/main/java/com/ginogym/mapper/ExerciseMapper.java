package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.presentation.request.CreateExerciseRequest;
import com.ginogym.presentation.request.ModifyExerciseRequest;

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
        return dto;
    }
    
    public Exercise toEntity(ExerciseDTO dto) {
        return mapper.map(dto, Exercise.class);
    }
    
    public Exercise requestToEntity(CreateExerciseRequest request) {
        return mapper.map(request, Exercise.class);
    }
    
    public void updateExerciseFromDTO(ExerciseDTO dto, Exercise exercise) {
        if (dto.getName() != null) exercise.setName(dto.getName());
    }
    
    public void requestUpdateExerciseFromDTO(ModifyExerciseRequest request, Exercise exercise) {
        if (request.getName() != null) exercise.setName(request.getName());
    }

}


