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

        // Gestione relazioni manuale
        if (entity.getExercise() != null) {
            dto.setExercises(entity.getExercise().getName()); // o id, a seconda del DTO
        }

        if (entity.getDaysOfWeek() != null) {
            dto.setDayOfWeek(entity.getDaysOfWeek().getDescrizione()); // o id, se preferisci
        }

        return dto;
    }

    // ✅ Conversione da DTO a Entity
    public ExerciseDetail toEntity(ExerciseDetailDTO dto) {
        if (dto == null) {
            return null;
        }

        ExerciseDetail entity = new ExerciseDetail();
        entity.setId(dto.getId());
        entity.setSerie(dto.getSerie());
        entity.setRipetizioni(dto.getRipetizioni());
        entity.setRecupero(dto.getRecupero());
        entity.setPeso(dto.getPeso());

        // Questi vanno gestiti fuori o tramite repository se devi caricarli da DB
        // Ad esempio: se hai un service o repository per recuperarli
        if (dto.getExercises() != null) {
            Exercise esercizio = new Exercise();
            esercizio.setName(dto.getExercises()); // oppure setId(dto.getExercisesId());
            entity.setExercise(esercizio);
        }

        if (dto.getDayOfWeek() != null) {
            DayOfWeek giorno = new DayOfWeek();
            giorno.setDescrizione(dto.getDayOfWeek()); // oppure setId(dto.getDayOfWeekId());
            entity.setDaysOfWeek(giorno);
        }

        return entity;
    }

    public void updateExerciseDetailFromDTO(ExerciseDetailDTO dto, ExerciseDetail exerciseDetail) {
        if (dto.getSerie() !=null) exerciseDetail.setSerie(dto.getSerie());
        if (dto.getRipetizioni() !=null) exerciseDetail.setRipetizioni(dto.getRipetizioni());
        if (dto.getRecupero() !=null) exerciseDetail.setRecupero(dto.getRecupero());
        if (dto.getPeso() !=null) exerciseDetail.setPeso(dto.getPeso());
        if (dto.getExercises() !=null) {
           Exercise esercizioOptional = exerciseDetail.getExercise();
            if (esercizioOptional != null) {
                exerciseDetail.setExercise(esercizioOptional);
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
