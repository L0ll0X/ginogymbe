package com.example.business;

import java.util.List;

import com.example.data.GruppoMuscolareEntity;

public interface GruppoMuscolare  {

	public List<GruppoMuscolareEntity> getAll();
	GruppoMuscolareEntity findById(Long id);
	GruppoMuscolareEntity save(GruppoMuscolareEntity entity);
	void delete(Long id);
}
