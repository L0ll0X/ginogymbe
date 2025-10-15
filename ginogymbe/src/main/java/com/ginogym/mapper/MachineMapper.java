package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.repositories.MuscleGroupRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class MachineMapper {

    private final ModelMapper mapper;

    public MachineMapper (ModelMapper mapper, MuscleGroupRepository muscleGroupRepository) {
        this.mapper=mapper;
    }

    public MachineDTO toDTO(Machine machine) {
        MachineDTO dto = mapper.map(machine, MachineDTO.class);
        if (machine.getName() !=null) {
            dto.setName(machine.getName());
        }
        return dto;
    }

    public Machine toEntity(MachineDTO dto) {
        return mapper.map(dto, Machine.class);
    }

    public void updateMachineFromDTO(MachineDTO dto, Machine machine) {
        if (dto.getName() != null) machine.setName(dto.getName());
        if (dto.getDescription() !=null) machine.setDescription(dto.getDescription());
        if (dto.getMuscleGroup() !=null) {
           MuscleGroup gruppoMuscolareOptional = machine.getMuscleGroup();
            if (gruppoMuscolareOptional != null) {
                machine.setMuscleGroup(gruppoMuscolareOptional);
            } else {
                throw new EntityNotFoundException("MuscleGroup non trovato con nome: " + dto.getMuscleGroup());
            }
        }
        }

        

    

}
