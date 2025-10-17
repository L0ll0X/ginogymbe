package com.ginogym.business;

import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.presentation.requests.CreateMachineRequest;
import com.ginogym.presentation.requests.ModifyMachineRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MachineService  {

    Page<MachineDTO> getAllMachines(Pageable pageable);

    Optional<MachineDTO> getMachineById(Long id);

    // Page<MachineDTO> getMachinesByMuscleGroup(String muscleGroupName, Pageable pageable);

    MachineDTO createMachine(CreateMachineRequest machineDTO);

    MachineDTO updateMachine(Long id, ModifyMachineRequest machineDTO);

    void deleteMachine(Long id);
}