package com.ginogym.business.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginogym.business.ExerciseService;
import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.business.DTOs.MuscleGroupDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.repositories.ExerciseRepository;
import com.ginogym.data.repositories.MachineRepository;
import com.ginogym.data.repositories.MuscleGroupRepository;
import com.ginogym.mapper.ExerciseMapper;
import com.ginogym.presentation.requests.CreateExerciseRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper mapper;
    private final MuscleGroupRepository muscleGroupRepository;
    private final MachineRepository machineRepository;

    @Override
    public Page<ExerciseDTO> getAllExercises(Pageable pageable) {
       return exerciseRepository.findAllWithRelations(pageable)
            .map(mapper::toDTO);
    }

    @Override
    public Optional<ExerciseDTO> getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<ExerciseDTO> getExerciseByName(String name) {
        return exerciseRepository.findByName(name)
                .map(mapper::toDTO);
    }

    @Override
    public Page<ExerciseDTO> getExerciseByMuscleGroup(String muscleGroupName, Pageable pageable) {
    return exerciseRepository.findByMuscleGroupNameWithRelations(muscleGroupName, pageable)
           .map(mapper::toDTO); 
}

    @Override
    public Page<ExerciseDTO> getExerciseByMachine(String machineName, Pageable pageable) {
    return exerciseRepository. findByMachineNameWithRelations(machineName, pageable)
           .map(mapper::toDTO); 
}

    @Override
    public ExerciseDTO createExercise(CreateExerciseRequest exerciseRequest) {
    Exercise exercise = mapper.requestToEntity(exerciseRequest); 
    Long muscleGroupId = exerciseRequest.getMuscleGroupId();
    if (muscleGroupId != null && muscleGroupId > 0) {
        Optional<MuscleGroup> muscleGroup = muscleGroupRepository.findById(muscleGroupId);
        if (muscleGroup.isPresent()){
            exercise.setMuscleGroup(muscleGroup.get());
        }
        else  {
            //TODO gestire la mancanza dell'entità MuscleGroup non devo poter fare il salvataggio
        }
    } 
    Long machineId = exerciseRequest.getMachineId();
    if (machineId != null && machineId > 0) {
        Optional<Machine> machine = machineRepository.findById(machineId);
        if(machine.isPresent()){
            exercise.setMachine(machine.get());
        } else {
            //TODO gestire eccezione
        }
    }
    exerciseRepository.save(exercise); 
    return mapper.toDTO(exercise);
}
    @Override
    public ExerciseDTO updateExercise(Long id, ModifyExerciseRequest exerciseRequest) {
        Exercise esercizioDaModificare = exerciseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Exercise not found with ID: " + id));
        Long muscleGroupId = exerciseRequest.getMuscleGroupId();
         MuscleGroup muscleGroup = null;
        if (muscleGroupId != null && muscleGroupId > 0) {
            muscleGroup = muscleGroupRepository.getReferenceById(muscleGroupId);
        } else if (muscleGroupId != null && muscleGroupId == 0) {
            throw new IllegalArgumentException("MuscleGroup ID cannot be 0.");
        }
        Long machineId = exerciseRequest.getMachineId();
        Machine machine = null;
        if (machineId != null && machineId > 0) {
            machine = machineRepository.getReferenceById(machineId);
        } else if (machineId != null && machineId == 0) {
            throw new IllegalArgumentException("Machine ID cannot be 0.");
        }
    mapper.requestUpdateExerciseFromDTO(exerciseRequest, esercizioDaModificare, muscleGroup, machine);
    exerciseRepository.save(esercizioDaModificare); 
    return mapper.toDTO(esercizioDaModificare);
}

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

}
