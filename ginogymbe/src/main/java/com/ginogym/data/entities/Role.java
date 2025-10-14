package com.ginogym.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // es. "ADMIN", "TRAINER", "MEMBER"

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}