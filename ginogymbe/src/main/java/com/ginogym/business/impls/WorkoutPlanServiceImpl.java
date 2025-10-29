package com.ginogym.business.impls;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.business.WorkoutPlanService;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.ExerciseDetail;
import com.ginogym.data.entities.WorkoutPlan;
import com.ginogym.data.repositories.ExerciseDetailRepository;
import com.ginogym.data.repositories.ExerciseRepository;
import com.ginogym.data.repositories.ExerciseWorkoutPlanRepository;
import com.ginogym.data.repositories.WorkoutPlanRepository;
import com.ginogym.mapper.WorkoutPlanMapper;
import com.ginogym.presentation.requests.CreateExerciseDetailRequest;
import com.ginogym.presentation.requests.CreateWorkoutPlanRequest;
import com.ginogym.presentation.requests.ModifyWorkoutPlanRequest;
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
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDetailRepository exerciseDetailRepository;

    @Autowired
    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository,
            ExerciseWorkoutPlanRepository exerciseWorkoutPlanRepository, WorkoutPlanMapper workoutPlanMapper,
            ExerciseRepository exerciseRepository, ExerciseDetailRepository exerciseDetailRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.exerciseWorkoutPlanRepository = exerciseWorkoutPlanRepository;
        this.workoutPlanMapper = workoutPlanMapper;
        this.exerciseRepository = exerciseRepository;
        this.exerciseDetailRepository = exerciseDetailRepository;
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

    // @Override
    // public Page<WorkoutPlanDTO> getWorkoutPlansByUserId(Long userId, Pageable pageable) {
    //     return workoutPlanRepository.findByUserId(userId, pageable)
    //             .map(workoutPlanMapper::toDTO);
    // }

 @Override
    public WorkoutPlanDTO createWorkoutPlan(CreateWorkoutPlanRequest workoutPlanRequest) {
        WorkoutPlan workoutPlan = workoutPlanMapper.requestToEntity(workoutPlanRequest);
        WorkoutPlan savedPlan = workoutPlanRepository.save(workoutPlan);
        Long savedPlanId = savedPlan.getId();
        List<CreateExerciseDetailRequest> exerciseDetailsRequests = workoutPlanRequest.getExerciseDetails(); 
          if (exerciseDetailsRequests != null && !exerciseDetailsRequests.isEmpty()) {
        for (CreateExerciseDetailRequest detailRequest : exerciseDetailsRequests) {
            Long exerciseId = detailRequest.getIdEsercizio();
            if (exerciseId == null) {
                 throw new IllegalArgumentException("Exercise ID cannot be null for an exercise detail in the plan.");
            }
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new RuntimeException("Exercise not found with id " + exerciseId));                    
            ExerciseDetail detail = ExerciseDetail.builder()
                    .serie(detailRequest.getSerie())
                    .ripetizioni(detailRequest.getRipetizioni())
                    .recupero(detailRequest.getRecupero())
                    .peso(detailRequest.getPeso())
                    .exercise(exercise) 
                    .workoutPlans(savedPlan) 
                    .build();             
            exerciseDetailRepository.save(detail);
        }
    } 
    return workoutPlanMapper.toDTO(savedPlan);
}

    @Override
    public WorkoutPlanDTO updateWorkoutPlan(Long id, ModifyWorkoutPlanRequest workoutPlanRequest) {
        return workoutPlanRepository.findById(id)
                .map(existing -> {
                    workoutPlanMapper.requestUpdateWorkoutPlanFromDTO(workoutPlanRequest, existing);
                    WorkoutPlan updated = workoutPlanRepository.save(existing);
                    return workoutPlanMapper.toDTO(updated);
                }).orElseThrow(() -> new RuntimeException("WorkoutPlan not found with id " + id));
    }

    @Override
    public void deleteWorkoutPlan(Long id) {
        workoutPlanRepository.deleteById(id);
    }
}
