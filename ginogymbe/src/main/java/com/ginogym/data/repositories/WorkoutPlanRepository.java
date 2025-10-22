package com.ginogym.data.repositories;

import com.ginogym.data.entities.WorkoutPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

    Page<WorkoutPlan> findByUserId(Long userId, Pageable pageable);

}
