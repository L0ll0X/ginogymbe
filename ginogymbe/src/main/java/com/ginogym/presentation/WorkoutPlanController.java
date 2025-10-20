 package com.ginogym.presentation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.data.entities.WorkoutPlan;
import com.ginogym.mapper.WorkoutPlanMapper;
import com.ginogym.data.repositories.WorkoutPlanRepository;

@RestController
@RequestMapping("/api/workoutplans")
public class WorkoutPlanController {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutPlanMapper workoutPlanMapper;

    public WorkoutPlanController(WorkoutPlanRepository workoutPlanRepository, WorkoutPlanMapper workoutPlanMapper) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.workoutPlanMapper = workoutPlanMapper;
    }

   
    @GetMapping
    public ResponseEntity<List<WorkoutPlanDTO>> getAllWorkoutPlans() {
        List<WorkoutPlanDTO> dtos = workoutPlanRepository.findAll()
                .stream()
                .map(workoutPlanMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> getWorkoutPlanById(@PathVariable Long id) {
        return workoutPlanRepository.findById(id)
                .map(workoutPlanMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @PostMapping
    public ResponseEntity<WorkoutPlanDTO> createWorkoutPlan(@RequestBody WorkoutPlanDTO dto) {
        WorkoutPlan workoutPlan = workoutPlanMapper.toEntity(dto);
        WorkoutPlan saved = workoutPlanRepository.save(workoutPlan);
        return ResponseEntity.ok(workoutPlanMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> updateWorkoutPlan(@PathVariable Long id,
                                                            @RequestBody WorkoutPlanDTO dto) {
        return workoutPlanRepository.findById(id).map(existing -> {
            workoutPlanMapper.updateWorkoutPlanFromDTO(dto, existing);
            WorkoutPlan updated = workoutPlanRepository.save(existing);
            return ResponseEntity.ok(workoutPlanMapper.toDTO(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        return workoutPlanRepository.findById(id).map(existing -> {
            workoutPlanRepository.delete(existing);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

 