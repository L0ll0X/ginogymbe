package com.ginogym.presentation;

import org.springframework.web.bind.annotation.RestController;

import com.ginogym.business.ExerciseDetailService;
import com.ginogym.business.DTOs.ExerciseDetailDTO;
import com.ginogym.business.DTOs.MachineDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/exerciseDetails")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ExerciseDetailController {

    private final ExerciseDetailService exerciseDetailService;

    @GetMapping
    public ResponseEntity<Page<ExerciseDetailDTO>> getAll(Pageable pageable) {
        Page<ExerciseDetailDTO> exerciseDetail = exerciseDetailService.getAllExerciseDetails(pageable);
        return ResponseEntity.ok(exerciseDetail);
    }

    @GetMapping("/{id}")
     public ResponseEntity<?> getById(@RequestParam Long id) {
        Optional<ExerciseDetailDTO> exerciseDetail;
        try {
           exerciseDetail= exerciseDetailService.getExerciseDetailById(id);     
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return exerciseDetail.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
     public ResponseEntity<ExerciseDetailDTO> create(@RequestBody ExerciseDetailDTO dto) {
        ExerciseDetailDTO created = exerciseDetailService.createExerciseDetail(dto);
        return ResponseEntity.created(URI.create("/api/exerciseDetails/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
     public ResponseEntity<ExerciseDetailDTO> update(@PathVariable Long id, @RequestBody ExerciseDetailDTO dto) {
        ExerciseDetailDTO updated= exerciseDetailService.updateExerciseDetail(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseDetailService.deleteExerciseDetail(id);
        return ResponseEntity.noContent().build();
    }


    

    













}
