package com.ginogym.data.entities;
import java.sql.Blob;
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
    @Lob // indica un Large Object
    @Column(name = "image") // opzionale, puoi specificare il nome della colonna
    private byte[] image; // campo per salvare l’immagine
    private String name;          // e.g. "Leg Press"
    private String description;   // breve descrizione
    
  

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Exercise> exercises;
}
