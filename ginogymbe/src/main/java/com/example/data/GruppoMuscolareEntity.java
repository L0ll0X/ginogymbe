package com.example.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name ="Gruppi_Muscolari")
@Data               // genera getter, setter, equals, hashCode, toString
@NoArgsConstructor  // costruttore senza argomenti
@AllArgsConstructor // costruttore con tutti gli argomenti
@Builder            // abilita il pattern builder
public class GruppoMuscolareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // diventa private

    private String nome;
    private String descrizione;
}