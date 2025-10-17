package com.ginogym.business.impls;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ginogym.business.MachineService;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.data.entities.Machine;
import com.ginogym.data.repositories.MachineRepository;
import com.ginogym.mapper.MachineMapper;
import com.ginogym.presentation.requests.CreateMachineRequest;
import com.ginogym.presentation.requests.ModifyMachineRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final MachineMapper machineMapper;

    @Override
    public Page<MachineDTO> getAllMachines(Pageable pageable) {
        return machineRepository.findAll(pageable)
                .map(machineMapper::toDTO);
    }

    @Override
    public Optional<MachineDTO> getMachineById(Long id) {
        return machineRepository.findById(id)
                .map(machineMapper::toDTO);
    }

    // @Override
    // public Page<MachineDTO> getMachinesByMuscleGroup(String muscleGroupName, Pageable pageable) {
    //     Page page = machineRepository.findByMuscleGroup_Name(muscleGroupName, pageable);
    //     List<MachineDTO> paginatedItems = page.getContent().stream().map(x -> machineMapper.toDTO((Machine)x)).toList();
    //      return new PageImpl<>(
    //         paginatedItems,       
    //         pageable,             
    //         page.getTotalElements() 
    //     );       
    // }

    @Override
    public MachineDTO createMachine(CreateMachineRequest machineDTO) {
        Machine machine = machineMapper.requestToEntity(machineDTO);
        Machine saved = machineRepository.save(machine);
        return machineMapper.toDTO(saved);
    }

    public MachineDTO updateMachine(Long id, ModifyMachineRequest machineDTO) {
        Machine existing = machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found"));
        machineMapper.requestUpdateMachineFromDTO(machineDTO, existing);
        return machineMapper.toDTO(machineRepository.save(existing));
    }

    @Override
    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }

}
