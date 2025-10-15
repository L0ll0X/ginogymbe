package com.ginogym.business.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ginogym.business.ExerciseDetailService;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.data.entities.ExerciseDetail;
import com.ginogym.data.repositories.ExerciseDetailRepository;
import com.ginogym.mapper.ExerciseDetailMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExerciseDetailServiceImpl implements ExerciseDetailService {

    private final ExerciseDetailMapper exerciseDetailMapper;

    private final ExerciseDetailRepository exerciseDetailRepository;
    
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
        Page page =exerciseDetailRepository.findByExercise_Id(exerciseId, pageable);
        List<ExerciseDetailDTO> paginatedItems = page.getContent().stream().map(x -> exerciseDetailMapper.toDTO((ExerciseDetail)x)).toList();
         return new PageImpl<>(
            paginatedItems,       
            pageable,             
            page.getTotalElements() 
        );       
    }

    @Override
    public Page<ExerciseDetailDTO> getExerciseDetailByDayOfWeek(Long dayOfWeekId, Pageable pageable) {
        Page page =exerciseDetailRepository.findByDayOfWeek_Id(dayOfWeekId, pageable);
        List<ExerciseDetailDTO> paginatedItems = page.getContent().stream().map(x -> exerciseDetailMapper.toDTO((ExerciseDetail)x)).toList();
         return new PageImpl<>(
            paginatedItems,       
            pageable,             
            page.getTotalElements() 
        );       
    }

    @Override
    public ExerciseDetailDTO createExerciseDetail(ExerciseDetailDTO exerciseDetailDTO) {
        ExerciseDetail exerciseDetail = exerciseDetailMapper.toEntity(exerciseDetailDTO);
        ExerciseDetail saved = exerciseDetailRepository.save(exerciseDetail);
        return exerciseDetailMapper.toDTO(saved);
    }

    @Override
    public ExerciseDetailDTO updateExerciseDetail(Long id, ExerciseDetailDTO exerciseDetailDTO) {
        ExerciseDetail existing = exerciseDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ExerciseDetail not found"));
        exerciseDetailMapper.updateExerciseDetailFromDTO(exerciseDetailDTO, existing);
        return exerciseDetailMapper.toDTO(exerciseDetailRepository.save(existing));
    }

    @Override
    public void deleteExerciseDetail(Long id) {
        exerciseDetailRepository.deleteById(id);
    }

}


