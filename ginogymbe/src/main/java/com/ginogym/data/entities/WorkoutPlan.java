package com.ginogym.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id") 
    // private User user;

    @JsonManagedReference
    @ManyToMany(mappedBy = "workoutPlans")
    private List<ExerciseDetail> exerciseDetails = new ArrayList();
    
}
