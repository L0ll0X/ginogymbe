package com.example.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.GruppoMuscolare;
import com.example.data.GruppoMuscolareEntity;
import com.example.presentation.DTOs.GruppoMuscolareDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gruppi-muscolari")
@RequiredArgsConstructor
public class GruppoMuscolareController {

	@Autowired
    private final GruppoMuscolare service;

    @GetMapping
    public List<GruppoMuscolareDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GruppoMuscolareDTO getById(@PathVariable Long id) {
        GruppoMuscolareEntity entity = service.findById(id);
        return GruppoMuscolareDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descrizione(entity.getDescrizione())
                .build();
    }

    @PostMapping
    public GruppoMuscolareDTO create(@RequestBody GruppoMuscolareEntity entity) {
        GruppoMuscolareEntity saved = service.save(entity);
        return GruppoMuscolareDTO.builder()
                .id(saved.getId())
                .nome(saved.getNome())
                .descrizione(saved.getDescrizione())
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
