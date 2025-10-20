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
    // Usa il mapping integrato di Page
    return exerciseRepository.findByMuscleGroupNameWithRelations(muscleGroupName, pageable)
           .map(mapper::toDTO); // Ritorna direttamente Page<ExerciseDTO>
}

    @Override
    public Page<ExerciseDTO> getExerciseByMachine(String machineName, Pageable pageable) {
    // Chiama il metodo ottimizzato con JOIN FETCH (ad esempio)
    return exerciseRepository. findByMachineNameWithRelations(machineName, pageable)
           // Usa la comoda funzione map() di Spring Data Page
           .map(mapper::toDTO); 
}

    @Override
    public ExerciseDTO createExercise(CreateExerciseRequest exerciseRequest) {
    Exercise exercise = mapper.requestToEntity(exerciseRequest); 
    
    // Gestione MuscleGroup
    Long muscleGroupId = exerciseRequest.getMuscleGroupId();
    
    // Controlla se l'ID è presente E DIVERSO da 0
    if (muscleGroupId != null && muscleGroupId > 0) {
        MuscleGroup muscleGroup = muscleGroupRepository.getReferenceById(muscleGroupId);
        exercise.setMuscleGroup(muscleGroup);
    } 
    // Se è null (nessuna selezione) o 0, viene semplicemente ignorato.

    // Gestione Machine
    Long machineId = exerciseRequest.getMachineId();
    if (machineId != null && machineId > 0) {
        Machine machine = machineRepository.getReferenceById(machineId);
        exercise.setMachine(machine);
    }
    
    // ... (altre logiche)

    exerciseRepository.save(exercise); 
    return mapper.toDTO(exercise);
}
    @Override
// Assicurati che questo metodo sia annotato con @Transactional
public ExerciseDTO updateExercise(Long id, ModifyExerciseRequest exerciseRequest) {
    // 1. Carica l'entità principale da modificare (Ora è gestita/managed)
    Exercise esercizioDaModificare = exerciseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Exercise not found with ID: " + id));

    // 2. Recupera e assegna MuscleGroup
    Long muscleGroupId = exerciseRequest.getMuscleGroupId();
    MuscleGroup muscleGroup = null;
    
    // Controlla per null E per valori > 0
    if (muscleGroupId != null && muscleGroupId > 0) {
        // Usa getReferenceById(): più efficiente in un contesto di aggiornamento
        muscleGroup = muscleGroupRepository.getReferenceById(muscleGroupId);
    } else if (muscleGroupId != null && muscleGroupId == 0) {
         // Lancia un errore chiaro se ricevi 0 (per debug lato client)
        throw new IllegalArgumentException("MuscleGroup ID cannot be 0.");
    }
    // Se è null, muscleGroup resta null (dissociando la relazione se necessario)


    // 3. Recupera e assegna Machine
    Long machineId = exerciseRequest.getMachineId();
    Machine machine = null;
    
    // Controlla per null E per valori > 0
    if (machineId != null && machineId > 0) {
        // Usa getReferenceById(): più efficiente
        machine = machineRepository.getReferenceById(machineId);
    } else if (machineId != null && machineId == 0) {
        throw new IllegalArgumentException("Machine ID cannot be 0.");
    }
    
    // 4. Aggiorna l'entità gestita con i nuovi valori e le relazioni
    // Il mapper deve impostare i campi e le relazioni sull'oggetto 'esercizioDaModificare' esistente.
    mapper.requestUpdateExerciseFromDTO(exerciseRequest, esercizioDaModificare, muscleGroup, machine);
    
    // 5. Salva e Mappa
    // L'entità è "managed", l'UPDATE avviene al commit della transazione.
    // Manteniamo save() per esplicita sicurezza, ma l'aggiornamento avviene anche senza.
    exerciseRepository.save(esercizioDaModificare); 
    
    return mapper.toDTO(esercizioDaModificare);
}

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

}
