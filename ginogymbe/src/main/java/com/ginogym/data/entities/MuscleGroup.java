package com.ginogym.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "muscle_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // es. "Chest", "Back", "Legs", "Shoulders"

    @OneToMany(mappedBy = "muscleGroup", cascade = CascadeType.ALL)
    private List<Exercise> exercises;
}