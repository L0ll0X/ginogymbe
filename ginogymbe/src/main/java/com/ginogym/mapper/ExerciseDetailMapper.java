package com.ginogym.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.business.DTOs.MuscleGroupDTO;
import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.DayOfWeek;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.ExerciseDetail;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.entities.WorkoutPlan;
import com.ginogym.presentation.requests.CreateExerciseDetailRequest;
import com.ginogym.presentation.requests.CreateExerciseRequest;
import com.ginogym.presentation.requests.ModifyExerciseDetailRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ExerciseDetailMapper {

private final ModelMapper mapper;

    public ExerciseDetailMapper (ModelMapper mapper) {
        this.mapper=mapper;
    }

    public ExerciseDetailDTO toDTO(ExerciseDetail entity) {
        if (entity == null) {
            return null;
        }
        ExerciseDetailDTO dto = new ExerciseDetailDTO();
        dto.setId(entity.getId());
        dto.setSerie(entity.getSerie());
        dto.setRipetizioni(entity.getRipetizioni());
        dto.setRecupero(entity.getRecupero());
        dto.setPeso(entity.getPeso());
        if (entity.getExercise() != null) {
            dto.setExercise(mapper.map(entity.getExercise(), ExerciseDTO.class));
        }
        return dto;
    }

    public ExerciseDetail toEntity(ExerciseDetailDTO dto) {
         return mapper.map(dto, ExerciseDetail.class);
    }

    public ExerciseDetail requestToEntity(CreateExerciseDetailRequest request) {
        ExerciseDetail e = mapper.map(request, ExerciseDetail.class);
        e.setId(null);
        return e;
    }

    // public void updateExerciseDetailFromDTO(ExerciseDetailDTO dto, ExerciseDetail exerciseDetail) {
    //     if (dto.getSerie() !=null) exerciseDetail.setSerie(dto.getSerie());
    //     if (dto.getRipetizioni() !=null) exerciseDetail.setRipetizioni(dto.getRipetizioni());
    //     if (dto.getRecupero() !=null) exerciseDetail.setRecupero(dto.getRecupero());
    //     if (dto.getPeso() !=null) exerciseDetail.setPeso(dto.getPeso());
    //     if (dto.getExercise() !=null) {
    //        Exercise esercizioOptional = exerciseDetail.getExercise();
    //         if (esercizioOptional != null) {
    //             exerciseDetail.setExercise(esercizioOptional);
    //         } else {
    //             throw new EntityNotFoundException("Exercise non trovato con nome: " + dto.getExercise());
    //         }
    //     }
    //     if (dto.getWorkoutPlan() !=null) {
    //        WorkoutPlan workoutPlanOptional = exerciseDetail.getWorkoutPlan();
    //         if (workoutPlanOptional != null) {
    //             exerciseDetail.setWorkoutPlan(workoutPlanOptional);
    //         } else {
    //             throw new EntityNotFoundException("WorkoutPlan non trovato con nome: " + dto.getWorkoutPlan());
    //         }
    //     }
    //     }

         public void requestUpdateExerciseDetailFromDTO(ModifyExerciseDetailRequest request, ExerciseDetail exerciseDetail, Exercise exercise, WorkoutPlan workoutPlan) {
        if (request.getSerie() !=null) exerciseDetail.setSerie(request.getSerie());
        if (request.getRipetizioni() !=null) exerciseDetail.setRipetizioni(request.getRipetizioni());
        if (request.getRecupero() !=null) exerciseDetail.setRecupero(request.getRecupero());
        if (request.getPeso() !=null) exerciseDetail.setPeso(request.getPeso());
        if (exercise !=null) {
                exerciseDetail.setExercise(exercise);
        }
    }

}
