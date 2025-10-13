package com.example.business.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.business.UtenteService;
import com.example.data.UtenteEntity;
import com.example.data.repositories.UtenteRepository;
import com.example.presentation.DTOs.UtenteDTO;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository repository;

    @Override
    public List<UtenteDTO> getAll() {
        return repository.findAll().stream()
            .map(x -> new UtenteDTO(
                x.getId(),
                x.getNome(),
                x.getCognome(),
                x.getNumeroDiCellulare(),
                x.getEmail()
            ))
            .toList();
    }

    @Override
    public UtenteDTO findById(Long id) {
        return repository.findById(id)
            .map(x -> new UtenteDTO(
                x.getId(),
                x.getNome(),
                x.getCognome(),
                x.getNumeroDiCellulare(),
                x.getEmail()
            ))
            .orElse(null);
    }

    @Override
    public UtenteDTO save(UtenteEntity entity) {
        UtenteEntity saved = repository.save(entity);
        return new UtenteDTO(
            saved.getId(),
            saved.getNome(),
            saved.getCognome(),
            saved.getNumeroDiCellulare(),
            saved.getEmail()
        );
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NullPointerException("Utente non trovato.");
        }
        repository.deleteById(id);
    }
}
