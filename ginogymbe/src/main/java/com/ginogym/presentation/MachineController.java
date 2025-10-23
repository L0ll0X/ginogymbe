package com.ginogym.presentation;

import java.net.URI;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginogym.business.MachineService;
import com.ginogym.business.DTOs.MachineDTO;
import com.ginogym.business.DTOs.PaginationResponse;
import com.ginogym.presentation.requests.CreateMachineRequest;
import com.ginogym.presentation.requests.ModifyMachineRequest;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/machines")
@CrossOrigin(origins = "http://localhost:4200") 
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    
    @GetMapping
    public ResponseEntity<PaginationResponse<MachineDTO>> getAll(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {

        Page<MachineDTO> machines = machineService.getAllMachines(pageable);
        PaginationResponse<MachineDTO> response = new PaginationResponse<>(machines);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<MachineDTO> machine = machineService.getMachineById(id);
        return machine.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MachineDTO> create(@RequestBody CreateMachineRequest dto) {
        MachineDTO created = machineService.createMachine(dto);
        return ResponseEntity.created(URI.create("/api/machines/" + created.getId()))
                             .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineDTO> update(@PathVariable Long id, @RequestBody ModifyMachineRequest dto) {
        MachineDTO updated = machineService.updateMachine(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        machineService.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }
}
