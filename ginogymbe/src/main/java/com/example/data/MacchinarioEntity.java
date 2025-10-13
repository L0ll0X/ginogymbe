package com.example.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "macchinario")
@Data // ✅ Lombok genera getter, setter, toString, equals, hashCode
@NoArgsConstructor // ✅ Costruttore vuoto
@AllArgsConstructor // ✅ Costruttore con tutti i campi
public class MacchinarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;
}
//Help → About Eclipse IDE → Installation Details → Configuration