package com.ginogym.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginogym.business.MuscleGroupService;
import com.ginogym.business.DTOs.MuscleGroupDTO;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping ("/api/musclegroups")
@CrossOrigin(origins = "http://localhost:4200") 
@RequiredArgsConstructor
public class MuscleGroupController {
    
    private final MuscleGroupService muscleGroupService;

    @GetMapping
    public ResponseEntity<Page<MuscleGroupDTO>> getAll(Pageable pageable) {
        Page<MuscleGroupDTO> muscleGroups = muscleGroupService.getAllMuscleGroups(pageable);
        return ResponseEntity.ok(muscleGroups);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<MuscleGroupDTO> muscleGroup;
        try {
           muscleGroup  = muscleGroupService.getMuscleGroupById(id);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return muscleGroup.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<MuscleGroupDTO> create(@RequestBody MuscleGroupDTO dto) {
        MuscleGroupDTO created = muscleGroupService.createMuscleGroup(dto);
        return ResponseEntity.created(URI.create("/api/musclegroups/" + created.getId())).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MuscleGroupDTO> update(@PathVariable Long id, @RequestBody MuscleGroupDTO dto) {
        MuscleGroupDTO updated = muscleGroupService.updateMuscleGroup(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        muscleGroupService.deleteMuscleGroup(id);
        return ResponseEntity.noContent().build();
    }

}
