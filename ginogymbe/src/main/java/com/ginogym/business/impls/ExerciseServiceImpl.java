package com.ginogym.business.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ginogym.business.ExerciseService;
import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.repositories.ExerciseRepository;
import com.ginogym.mapper.ExerciseMapper;
import com.ginogym.presentation.requests.CreateExerciseRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper mapper;

    @Override
    public Page<ExerciseDTO> getAllExercises(Pageable pageable) {
        return exerciseRepository.findAll(pageable)
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
        Page page = exerciseRepository.findByMuscleGroup_Name(muscleGroupName, pageable);
        List<ExerciseDTO> paginatedItems = page.getContent().stream().map(x -> mapper.toDTO((Exercise)x)).toList();
         return new PageImpl<>(
            paginatedItems,       
            pageable,             
            page.getTotalElements() 
        );       
    }

    @Override
    public Page<ExerciseDTO> getExerciseByMachine(String machineName, Pageable pageable) {
        Page page = exerciseRepository.findByMachine_Name(machineName, pageable);
        List<ExerciseDTO> paginatedItems = page.getContent().stream().map(x -> mapper.toDTO((Exercise)x)).toList();
         return new PageImpl<>(
            paginatedItems,       
            pageable,             
            page.getTotalElements() 
        );       
    }

    @Override
    public ExerciseDTO createExercise(CreateExerciseRequest exerciseRequest) {
        Exercise exercise =mapper.requestToEntity(exerciseRequest);
        Exercise saved = exerciseRepository.save(exercise);
        return mapper.toDTO(saved);
    }

    @Override
    public ExerciseDTO updateExercise(Long id, ModifyExerciseRequest exerciseRequest) {
        Exercise existing = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
        mapper.requestUpdateExerciseFromDTO(exerciseRequest, existing);
        return mapper.toDTO(exerciseRepository.save(existing));
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

}
