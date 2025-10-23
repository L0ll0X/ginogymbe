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
import com.ginogym.business.DTOs.WorkoutPlanDTO;
import com.ginogym.mapper.ExerciseMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workoutplans")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;
    private final ExerciseMapper exerciseMapper;

    @GetMapping
    public ResponseEntity<Page<WorkoutPlanDTO>> getAll(Pageable pageable) {
        Page<WorkoutPlanDTO> workoutPlan = workoutPlanService.getAllWorkoutPlans(pageable);
        return ResponseEntity.ok(workoutPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<WorkoutPlanDTO> workoutPlan;
        try {
            workoutPlan = workoutPlanService.getWorkoutPlanById(id);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return workoutPlan.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/workoutplan-exercises/{id}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByWorkoutPlan(@PathVariable Long id) {
        List<ExerciseDTO> exercises = workoutPlanService.getExercisesByWorkoutPlan(id)
                .stream()
                .map(exerciseMapper::toDTO) // se hai un mapper
                .toList();

        return ResponseEntity.ok(exercises);
    }

    @PostMapping
    public ResponseEntity<WorkoutPlanDTO> createWorkoutPlan(@RequestBody WorkoutPlanDTO dto) {
        WorkoutPlanDTO created = workoutPlanService.createWorkoutPlan(dto);
        return ResponseEntity.created(URI.create("/api/workoutPlans/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> update(@PathVariable Long id, @RequestBody WorkoutPlanDTO dto) {
        WorkoutPlanDTO updated = workoutPlanService.updateWorkoutPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }

}
