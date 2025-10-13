package com.example.business.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.business.GruppoMuscolare;
import com.example.data.GruppoMuscolareEntity;
import com.example.data.repositories.GruppoMuscolareRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GruppoMuscolareImpl implements GruppoMuscolare{
	
	@Autowired
	private GruppoMuscolareRepository repository;
	@Override
	public List<GruppoMuscolareEntity> getAll() {
		return repository.findAll() ;
	}
	@Override
	public GruppoMuscolareEntity findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("GruppoMuscolareEntity non trovato con id: " + id));
	}
	@Override
	public GruppoMuscolareEntity save(GruppoMuscolareEntity entity) {
		return repository.save(entity);
	}
	@Override
	public void delete(Long id) {
	 repository.deleteById(id);
		
	}

}
