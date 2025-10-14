package com.ginogym.business;

import com.ginogym.business.DTOs.MuscleGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MuscleGroupService {

    Page<MuscleGroupDTO> getAllMuscleGroups(Pageable pageable);

    Optional<MuscleGroupDTO> getMuscleGroupById(Long id);

    Optional<MuscleGroupDTO> getMuscleGroupByName(String name);

    MuscleGroupDTO createMuscleGroup(MuscleGroupDTO dto);

    MuscleGroupDTO updateMuscleGroup(Long id, MuscleGroupDTO dto);

    void deleteMuscleGroup(Long id);
}