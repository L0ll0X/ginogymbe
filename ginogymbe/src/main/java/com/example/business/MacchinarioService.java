package com.example.business;

import java.util.List;
import com.example.presentation.DTOs.MacchinarioDTO;

public interface MacchinarioService {

   
    List<MacchinarioDTO> getAll();
    MacchinarioDTO findById(Long id);    
    MacchinarioDTO save(MacchinarioDTO dto);
    void delete(Long id);
}
