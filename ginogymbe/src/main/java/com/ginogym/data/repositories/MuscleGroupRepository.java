package com.ginogym.data.repositories;

import com.ginogym.data.entities.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {

    Optional<MuscleGroup> findByName(String name);
     Optional<MuscleGroup>findById(Long id);

}