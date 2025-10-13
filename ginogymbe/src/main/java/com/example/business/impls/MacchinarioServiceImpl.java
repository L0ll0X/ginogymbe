package com.example.business.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.business.MacchinarioService;
import com.example.presentation.DTOs.MacchinarioDTO;
import com.example.data.MacchinarioEntity;
import com.example.data.repositories.MacchinarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MacchinarioServiceImpl implements MacchinarioService {

    @Autowired
    private MacchinarioRepository repository;

    @Override
    public List<MacchinarioDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> new MacchinarioDTO(entity.getId(), entity.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public MacchinarioDTO findById(Long id) {
        MacchinarioEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Macchinario non trovato con id: " + id));
        return new MacchinarioDTO(entity.getId(), entity.getNome());
    }

    @Override
    public MacchinarioDTO save(MacchinarioDTO dto) {
        MacchinarioEntity entity = new MacchinarioEntity();
        entity.setId(dto.getId()); // utile per update
        entity.setNome(dto.getNome());

        MacchinarioEntity saved = repository.save(entity);
        return new MacchinarioDTO(saved.getId(), saved.getNome());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

