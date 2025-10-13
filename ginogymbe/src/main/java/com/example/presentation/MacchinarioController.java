package com.example.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.business.MacchinarioService;
import com.example.data.MacchinarioEntity;

import lombok.Data;

@RestController
@RequestMapping("/macchinari")
public class MacchinarioController {

    @Autowired
    private MacchinarioService macchinarioService;


    @GetMapping
    public List<MacchinarioEntity> getAll() {
        return macchinarioService.getAll();
    }

    @GetMapping("/{id}")
    public MacchinarioEntity getById(@PathVariable Long id) {
        return macchinarioService.findById(id);
    }

    @PostMapping
    public MacchinarioEntity create(@RequestBody MacchinarioEntity entity) {
        return macchinarioService.save(entity);
    }
    

    @PutMapping
    public MacchinarioEntity update(@PathVariable Long id, @RequestBody MacchinarioEntity entity) {
        //entity.setId(id); 
        return macchinarioService.save(entity);
    }

   /* @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        macchinarioService.delete(id);
    }*/
}
