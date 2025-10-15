package com.ginogym.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.data.entities.DayOfWeek;
import com.ginogym.data.entities.Exercise;
import com.ginogym.data.entities.ExerciseDetail;
import jakarta.persistence.EntityNotFoundException;

@Component
public class ExerciseDetailMapper {

private final ModelMapper mapper;

    public ExerciseDetailMapper (ModelMapper mapper) {
        this.mapper=mapper;
    }

     public ExerciseDetailDTO toDTO(ExerciseDetail exerciseDetail) {
        return mapper.map(exerciseDetail, ExerciseDetailDTO.class);

    }

    public ExerciseDetail toEntity(ExerciseDetailDTO dto) {
        return mapper.map(dto, ExerciseDetail.class);
    }

    public void updateExerciseDetailFromDTO(ExerciseDetailDTO dto, ExerciseDetail exerciseDetail) {
        if (dto.getSerie() !=null) exerciseDetail.setSerie(dto.getSerie());
        if (dto.getRipetizioni() !=null) exerciseDetail.setRipetizioni(dto.getRipetizioni());
        if (dto.getRecupero() !=null) exerciseDetail.setRecupero(dto.getRecupero());
        if (dto.getPeso() !=null) exerciseDetail.setPeso(dto.getPeso());
        if (dto.getExercises() !=null) {
           Exercise esercizioOptional = exerciseDetail.getExercises();
            if (esercizioOptional != null) {
                exerciseDetail.setExercises(esercizioOptional);
            } else {
                throw new EntityNotFoundException("Exercise non trovato con nome: " + dto.getExercises());
            }
        }
        if (dto.getDayOfWeek() !=null) {
           DayOfWeek giornoSettimanaOptional = exerciseDetail.getDaysOfWeek();
            if (giornoSettimanaOptional != null) {
                exerciseDetail.setDaysOfWeek(giornoSettimanaOptional);
            } else {
                throw new EntityNotFoundException("daysOfWeek non trovato con nome: " + dto.getDayOfWeek());
            }
        }
        }


}
