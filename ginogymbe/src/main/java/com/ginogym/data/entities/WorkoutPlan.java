package com.ginogym.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workoutPlan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startDate; 
    private String endDate; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") 
    private User user;

    @ManyToMany(mappedBy = "workoutPlans")
    private Set<Exercise> exercises = new HashSet<>();
    
}
