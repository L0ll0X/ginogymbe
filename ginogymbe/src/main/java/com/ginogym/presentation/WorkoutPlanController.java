package com.ginogym.presentation;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ginogym.business.WorkoutPlanService;
import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.business.DTOs.PaginationResponse;
import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.mapper.ExerciseMapper;
import com.ginogym.presentation.requests.CreateWorkoutPlanRequest;
import com.ginogym.presentation.requests.ModifyWorkoutPlanRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RequestMapping("/api/workoutplans")
@RequiredArgsConstructor
@RestController
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;
    private final ExerciseMapper exerciseMapper;

    @GetMapping
    public ResponseEntity<PaginationResponse<WorkoutPlanDTO>> getAll(Pageable pageable) {
        Page<WorkoutPlanDTO> workoutPlan = workoutPlanService.getAllWorkoutPlans(pageable);
        PaginationResponse<WorkoutPlanDTO> response = new PaginationResponse<>(workoutPlan);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> getById(@PathVariable Long id) {
       return workoutPlanService.getWorkoutPlanById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/workoutplan-exercises/{id}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByWorkoutPlan(@PathVariable Long id) {
        List<ExerciseDTO> exercises = workoutPlanService.getExercisesByWorkoutPlan(id)
                .stream()
                .map(exerciseMapper::toDTO) 
                .toList();

        return ResponseEntity.ok(exercises);
    }

    @PostMapping
    public ResponseEntity<WorkoutPlanDTO> createWorkoutPlan(@RequestBody CreateWorkoutPlanRequest dto) {
        WorkoutPlanDTO created = workoutPlanService.createWorkoutPlan(dto);
        return ResponseEntity.created(URI.create("/api/workoutPlans/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> update(@PathVariable Long id, @RequestBody ModifyWorkoutPlanRequest dto) {
        WorkoutPlanDTO updated = workoutPlanService.updateWorkoutPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }

}
