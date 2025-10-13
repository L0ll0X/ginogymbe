package com.example.business.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.business.MacchinarioService;
import com.example.data.MacchinarioEntity;
import com.example.data.repositories.MacchinarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MacchinarioServiceImpl implements MacchinarioService{
	@Autowired
	private MacchinarioRepository repository;

	@Override
	public List<MacchinarioEntity> getAll() {
	
		return repository.findAll();
	}

	@Override
	public MacchinarioEntity findById(Long id) {
	
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("MacchinarioEntity non trovato con id:"+id));
			
		
	}

	@Override
	public MacchinarioEntity save(MacchinarioEntity entity) {
		
		return repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		 repository.deleteById(id);	
		
	}


}
