package com.ginogym.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "workout_plan")
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

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExerciseDetail> exerciseDetails;
}
