package com.ginogym.business.impls;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ginogym.business.MuscleGroupService;
import com.ginogym.business.DTOs.MuscleGroupDTO;
import com.ginogym.data.entities.MuscleGroup;
import com.ginogym.data.entities.User;
import com.ginogym.data.repositories.MuscleGroupRepository;
import com.ginogym.mapper.MuscleGroupMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MuscleGroupServiceImpl implements MuscleGroupService{

    private final MuscleGroupRepository muscleGroupRepository;
    private final MuscleGroupMapper mapper;
    
    @Override
    public Page<MuscleGroupDTO> getAllMuscleGroups(Pageable pageable) {
        return muscleGroupRepository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<MuscleGroupDTO> getMuscleGroupById(Long id) {
        return muscleGroupRepository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<MuscleGroupDTO> getMuscleGroupByName(String name) {
        return muscleGroupRepository.findByName(name)
                .map(mapper::toDTO);
    }

    @Override
    public MuscleGroupDTO createMuscleGroup(MuscleGroupDTO muscleGroupDTO) {
        MuscleGroup muscleGroup =mapper.toEntity(muscleGroupDTO);
        MuscleGroup saved = muscleGroupRepository.save(muscleGroup);
        return mapper.toDTO(saved);
    }

    @Override
    public MuscleGroupDTO updateMuscleGroup(Long id, MuscleGroupDTO muscleGroupDTO) {
        MuscleGroup existing = muscleGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Muscle Group not found"));
        mapper.updateMuscleGroupFromDTO(muscleGroupDTO, existing);
        return mapper.toDTO(muscleGroupRepository.save(existing));
    }

    @Override
    public void deleteMuscleGroup(Long id) {
        muscleGroupRepository.deleteById(id);
    }

}
