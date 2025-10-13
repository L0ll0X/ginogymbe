package com.example.business;

import java.util.List;

import com.example.data.UtenteEntity;
import com.example.presentation.DTOs.UtenteDTO;

public interface UtenteService {

	
	List<UtenteDTO> getAll();
	UtenteDTO findById(Long id);
	UtenteDTO save(UtenteEntity entity);
	void delete(Long Id);
	
}
