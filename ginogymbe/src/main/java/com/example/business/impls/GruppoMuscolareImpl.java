package com.example.business.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.business.GruppoMuscolare;
import com.example.data.GruppoMuscolareEntity;
import com.example.data.repositories.GruppoMuscolareRepository;
import com.example.presentation.DTOs.GruppoMuscolareDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GruppoMuscolareImpl implements GruppoMuscolare {

    @Autowired
    private GruppoMuscolareRepository repository;

    @Override
    public List<GruppoMuscolareDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(entity -> new GruppoMuscolareDTO(
                        entity.getId(),
                        entity.getNome(),
                        entity.getDescrizione()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public GruppoMuscolareDTO findById(Long id) {
        GruppoMuscolareEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Gruppo muscolare non trovato con id: " + id));
        return new GruppoMuscolareDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescrizione()
        );
    }

    @Override
    public GruppoMuscolareDTO save(GruppoMuscolareDTO dto) {
        GruppoMuscolareEntity entity = new GruppoMuscolareEntity();
        entity.setId(dto.getId()); // utile per update
        entity.setNome(dto.getNome());
        entity.setDescrizione(dto.getDescrizione());

        GruppoMuscolareEntity saved = repository.save(entity);
        return new GruppoMuscolareDTO(
                saved.getId(),
                saved.getNome(),
                saved.getDescrizione()
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}