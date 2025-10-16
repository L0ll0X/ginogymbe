package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.MuscleGroupDTO;
import com.ginogym.data.entities.MuscleGroup;

@Component
public class MuscleGroupMapper {

private final ModelMapper mapper;

    public MuscleGroupMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public MuscleGroupDTO toDTO(MuscleGroup muscleGroup) {
        MuscleGroupDTO dto = mapper.map(muscleGroup, MuscleGroupDTO.class);
        if (muscleGroup.getName() != null) {
            dto.setName(muscleGroup.getName());
        }
        return dto;
    }

    public MuscleGroup toEntity(MuscleGroupDTO dto) {
        MuscleGroup entity = mapper.map(dto, MuscleGroup.class);
          if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        return entity;
    }

    public void updateMuscleGroupFromDTO(MuscleGroupDTO dto, MuscleGroup muscleGroup) {
    if (dto.getName() != null) muscleGroup.setName(dto.getName());
    }

}

