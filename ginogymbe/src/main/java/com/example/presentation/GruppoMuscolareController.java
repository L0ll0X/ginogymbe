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

import com.example.business.GruppoMuscolare;
import com.example.data.GruppoMuscolareEntity;

@Controller
@RequestMapping ("/gruppi-muscolari")
public class GruppoMuscolareController {
	
	@Autowired
	private GruppoMuscolare service;
	
	@GetMapping
	@ResponseBody
	public List<GruppoMuscolareEntity>getAll() {
		return service.getAll();
	}
	@GetMapping ("/{id}")
	@ResponseBody
	public GruppoMuscolareEntity getById(@PathVariable Long id) {
        return service.findById(id); 
     }
	@PostMapping
	@ResponseBody
	public GruppoMuscolareEntity create(@RequestBody GruppoMuscolareEntity entity) {
		return service.save(entity);
	}
	@DeleteMapping("/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
}
