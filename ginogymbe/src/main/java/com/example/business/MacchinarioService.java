package com.example.business;

import java.util.List;

import com.example.data.MacchinarioEntity;

public interface MacchinarioService {
	public List<MacchinarioEntity> getAll();
	MacchinarioEntity findById(Long id);
	MacchinarioEntity save(MacchinarioEntity entity);
	void delete(Long id);
}
