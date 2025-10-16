package com.ginogym.data.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "machines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // e.g. "Leg Press"
    private String description;   // breve descrizione
    
    @ManyToOne
    @JoinColumn(name = "muscle_group_id")
    @JsonIgnore
    private MuscleGroup muscleGroup;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Exercise> exercises;
}