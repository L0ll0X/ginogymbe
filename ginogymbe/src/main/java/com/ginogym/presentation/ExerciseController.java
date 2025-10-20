package com.ginogym.presentation;

import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ginogym.business.ExerciseService;
import com.ginogym.business.DTOs.ExerciseDTO;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.business.DTOs.PaginationResponse;
import com.ginogym.presentation.requests.CreateExerciseRequest;
import com.ginogym.presentation.requests.ModifyExerciseRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<Page<ExerciseDTO>> getAll(Pageable pageable) {
        Page<ExerciseDTO> exercise = exerciseService.getAllExercises(pageable);
         PaginationResponse<ExerciseDTO> response = new PaginationResponse<>(exercise);
        return ResponseEntity.ok(exercise);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        Optional<ExerciseDTO> exercise;
        try {
           exercise  = exerciseService.getExerciseById(id);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return exercise.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ExerciseDTO> create(@RequestBody CreateExerciseRequest dto) {
        ExerciseDTO created = exerciseService.createExercise(dto);
        return ResponseEntity.created(URI.create("/api/exercises/" + created.getId())).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> update(@PathVariable Long id, @RequestBody ModifyExerciseRequest dto) {
        ExerciseDTO updated = exerciseService.updateExercise(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

}
