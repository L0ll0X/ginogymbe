package com.ginogym.business.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ginogym.business.ExerciseDetailService;
import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.ExerciseDetail;
import com.ginogym.data.entities.ExerciseWorkoutPlan;
import com.ginogym.data.entities.ExerciseWorkoutPlanId;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.entities.WorkoutPlan;
import com.ginogym.data.repositories.DayOfWeekRepository;
import com.ginogym.data.repositories.ExerciseDetailRepository;
import com.ginogym.data.repositories.ExerciseRepository;
import com.ginogym.data.repositories.ExerciseWorkoutPlanRepository;
import com.ginogym.data.repositories.WorkoutPlanRepository;
import com.ginogym.mapper.ExerciseDetailMapper;
import com.ginogym.presentation.requests.CreateExerciseDetailRequest;
import com.ginogym.presentation.requests.ModifyExerciseDetailRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseDetailServiceImpl implements ExerciseDetailService {

    private final ExerciseDetailMapper exerciseDetailMapper;
    private final ExerciseDetailRepository exerciseDetailRepository;
    private final ExerciseRepository exerciseRepository; 
    private final WorkoutPlanRepository workoutPlanRepository; 
    
    @Override
    public Page<ExerciseDetailDTO> getAllExerciseDetails(Pageable pageable){
        return exerciseDetailRepository.findAll(pageable)
        .map(exerciseDetailMapper::toDTO);
    }

    @Override
    public Optional<ExerciseDetailDTO> getExerciseDetailById(Long id){
        return exerciseDetailRepository.findById(id)
         .map(exerciseDetailMapper::toDTO);
    }

    @Override
    public Page<ExerciseDetailDTO> getExerciseDetailByExercise(Long exerciseId, Pageable pageable) {
        return exerciseDetailRepository.findByExercise_Id(exerciseId, pageable)
                .map(exerciseDetailMapper::toDTO);
    }

    @Override
    public Page<ExerciseDetailDTO> getExerciseDetailByDayOfWeek(Long dayOfWeekId, Pageable pageable) {
        return exerciseDetailRepository.findByDaysOfWeek_Id(dayOfWeekId, pageable)
                .map(exerciseDetailMapper::toDTO);
    }

    @Override
    public Page<ExerciseDetailDTO> getExerciseDetailByWorkoutPlan(Long workoutPlanId, Pageable pageable) {
        return exerciseDetailRepository.findByWorkoutPlansIdWithDetails(workoutPlanId, pageable)
                .map(exerciseDetailMapper::toDTO);
    }

   @Override
public ExerciseDetailDTO createExerciseDetail(CreateExerciseDetailRequest exerciseDetailRequest) { 
    ExerciseDetail exerciseDetail = exerciseDetailMapper.requestToEntity(exerciseDetailRequest);
    Long exerciseId = exerciseDetailRequest.getIdEsercizio();
    if (exerciseId != null && exerciseId > 0) {
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId); 
          if (exercise.isPresent()){  
              exerciseDetail.setExercise(exercise.get());
          } else  {
            //TODO gestire la mancanza dell'entità MuscleGroup non devo poter fare il salvataggio
        }
    }
    Long workoutPlanId = exerciseDetailRequest.getIdWorkoutPlan();
              if (workoutPlanId != null && workoutPlanId > 0) {
          Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(workoutPlanId);  
            if(workoutPlan.isPresent()){    
              exerciseDetail.setWorkoutPlans(workoutPlan.get());
            } else  {
            //TODO gestire la mancanza dell'entità MuscleGroup non devo poter fare il salvataggio
        }
    }   
    exerciseDetailRepository.save(exerciseDetail);  
    return exerciseDetailMapper.toDTO(exerciseDetail);
}
   

    @Override
    public ExerciseDetailDTO updateExerciseDetail(Long id, ModifyExerciseDetailRequest exerciseDetailRequest) {
        ExerciseDetail existing = exerciseDetailRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ExerciseDetail not found"));
        Long exerciseId = exerciseDetailRequest.getIdEsercizio();
        Exercise exercise= null;
        if (exerciseId != null && exerciseId > 0) {
            exercise= exerciseRepository.getReferenceById(exerciseId);
        } else if (exerciseId != null && exerciseId == 0) {
            throw new IllegalArgumentException("Exercise ID cannot be 0.");
        }  
        Long workoutPlanId = exerciseDetailRequest.getIdWorkoutPlan();
        WorkoutPlan workoutPlan= null;
        if (workoutPlanId != null && workoutPlanId > 0) {
             workoutPlan = workoutPlanRepository.getReferenceById(workoutPlanId);
        } else if (workoutPlanId != null && workoutPlanId == 0) {  
            throw new IllegalArgumentException("WorkoutPlan ID cannot be 0.");
        }  
        exerciseDetailMapper.requestUpdateExerciseDetailFromDTO(exerciseDetailRequest, existing, exercise, workoutPlan);
        exerciseDetailRepository.save(existing); 
        return exerciseDetailMapper.toDTO(existing);    
    }

    @Override
    public void deleteExerciseDetail(Long id) {
        exerciseDetailRepository.deleteById(id);
    }

}


