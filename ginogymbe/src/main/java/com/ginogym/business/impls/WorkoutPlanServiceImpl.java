package com.ginogym.business.impls;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.business.WorkoutPlanService;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.WorkoutPlan;
import com.ginogym.data.repositories.ExerciseWorkoutPlanRepository;
import com.ginogym.data.repositories.WorkoutPlanRepository;
import com.ginogym.mapper.WorkoutPlanMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final ExerciseWorkoutPlanRepository exerciseWorkoutPlanRepository;
    private final WorkoutPlanMapper workoutPlanMapper;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository, ExerciseWorkoutPlanRepository exerciseWorkoutPlanRepository, WorkoutPlanMapper workoutPlanMapper) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.exerciseWorkoutPlanRepository = exerciseWorkoutPlanRepository;
        this.workoutPlanMapper = workoutPlanMapper;
    }

    @Override
    public Page<WorkoutPlanDTO> getAllWorkoutPlans(Pageable pageable) {
        return workoutPlanRepository.findAll(pageable)
                .map(workoutPlanMapper::toDTO);
    }

    @Override
    public List<Exercise> getExercisesByWorkoutPlan(Long workoutPlanId) {
        return exerciseWorkoutPlanRepository.findExercisesByWorkoutPlanId(workoutPlanId);
    }

    @Override
    public Optional<WorkoutPlanDTO> getWorkoutPlanById(Long id) {
        return workoutPlanRepository.findById(id)
                .map(workoutPlanMapper::toDTO);
    }

    @Override
    public Page<WorkoutPlanDTO> getWorkoutPlansByUserId(Long userId, Pageable pageable) {
        return workoutPlanRepository.findByUserId(userId, pageable)
                .map(workoutPlanMapper::toDTO);
    }

    @Override
    public WorkoutPlanDTO createWorkoutPlan(WorkoutPlanDTO workoutPlanDTO) {
        WorkoutPlan workoutPlan = workoutPlanMapper.toEntity(workoutPlanDTO);
        WorkoutPlan saved = workoutPlanRepository.save(workoutPlan);
        return workoutPlanMapper.toDTO(saved);
    }

  @Override
public WorkoutPlanDTO updateWorkoutPlan(Long id, WorkoutPlanDTO workoutPlanDTO) {
    return workoutPlanRepository.findById(id)
            .map(existing -> {
                workoutPlanMapper.updateWorkoutPlanFromDTO(workoutPlanDTO, existing);
                WorkoutPlan updated = workoutPlanRepository.save(existing);
                return workoutPlanMapper.toDTO(updated);
            }).orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id " + id));
}


    @Override
    public void deleteWorkoutPlan(Long id) {
        workoutPlanRepository.deleteById(id);
    }
}
