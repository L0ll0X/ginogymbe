package com.example.business;

import java.util.List;


import com.example.presentation.DTOs.GruppoMuscolareDTO;

public interface GruppoMuscolare  {

	public List<GruppoMuscolareDTO> getAll();
	GruppoMuscolareDTO findById(Long id);
	GruppoMuscolareDTO save(GruppoMuscolareDTO dto);
	void delete(Long id);
}
