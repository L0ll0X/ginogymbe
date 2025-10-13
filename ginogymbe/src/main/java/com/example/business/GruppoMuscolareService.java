package com.example.business;

import java.util.List;


import com.example.presentation.DTOs.GruppoMuscolareDTO;
import com.example.presentation.Requests.PageRequests;

public interface GruppoMuscolareService  {

	public List<GruppoMuscolareDTO> getAll(PageRequests request);
	GruppoMuscolareDTO findById(Long id);
	GruppoMuscolareDTO save(GruppoMuscolareDTO dto);
	void delete(Long id);
}
