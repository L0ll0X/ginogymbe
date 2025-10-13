package com.example.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.business.MacchinarioService;
import com.example.presentation.DTOs.MacchinarioDTO;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/macchinari")
public class MacchinarioController {

    @Autowired
    private MacchinarioService macchinarioService;

    @GetMapping
    public List<MacchinarioDTO> getAll() {
        return macchinarioService.getAll();
    }

    @GetMapping("/{id}")
    public MacchinarioDTO getById(@PathVariable Long id) {
        return macchinarioService.findById(id);
    }

    @PostMapping
    public MacchinarioDTO create(@RequestBody MacchinarioDTO dto) {
        return macchinarioService.save(dto);
    }

    @PutMapping("/{id}")
    public MacchinarioDTO update(@PathVariable Long id, @RequestBody MacchinarioDTO dto) {
        dto.setId(id); 
        return macchinarioService.save(dto);
    }

    /* @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        macchinarioService.delete(id);
    } */
}
