package com.ginogym.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Cliente {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String numeroCellulare;

    public Cliente() {}

    public Cliente(String nome, String cognome, String email, String numeroCellulare) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numeroCellulare = numeroCellulare;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNumeroCellulare() { return numeroCellulare; }
    public void setNumeroCellulare(String numeroCellulare) { this.numeroCellulare = numeroCellulare; }
}

