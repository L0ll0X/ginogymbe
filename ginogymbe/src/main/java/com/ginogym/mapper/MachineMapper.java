package com.ginogym.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.repositories.MuscleGroupRepository;
import com.ginogym.presentation.requests.CreateExerciseRequest;
import com.ginogym.presentation.requests.CreateMachineRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;
import com.ginogym.presentation.requests.ModifyMachineRequest;

import jakarta.persistence.EntityNotFoundException;

@Component
public class MachineMapper {

    private final ModelMapper mapper;

    public MachineMapper (ModelMapper mapper) {
        this.mapper=mapper;
    }

   public MachineDTO toDTO(Machine machine) {
    MachineDTO dto=mapper.map(machine,MachineDTO.class);
    if (machine.getName() != null) {
        dto.setName(machine.getName());
    }
    if(machine.getDescription()!=null) {
        dto.setDescription(machine.getDescription());
    }
    return dto;
}


    public Machine toEntity(MachineDTO dto) {
        return mapper.map(dto, Machine.class);
    }

     public Machine requestToEntity(CreateMachineRequest request) {
        return mapper.map(request, Machine.class);
    }

    public void requestUpdateMachineFromDTO(ModifyMachineRequest request, Machine machine) {
        if (request.getName() != null) machine.setName(request.getName());
        if (request.getDescription() !=null) machine.setDescription(request.getDescription());
    }

    public void updateMachineFromDTO(MachineDTO dto, Machine machine) {
        if (dto.getName() != null) machine.setName(dto.getName());
        if (dto.getDescription() !=null) machine.setDescription(dto.getDescription());
        //   if (dto.getMuscleGroup() != null) {
        // MuscleGroup mg = muscleGroupRepository.findById(dto.getMuscleGroup().getId())
        //     .orElseThrow(() -> new EntityNotFoundException(
        //         "MuscleGroup non trovato con id: " + dto.getMuscleGroup().getId()));
        // machine.setMuscleGroup(mg);
    // }
        }

        

    

}
