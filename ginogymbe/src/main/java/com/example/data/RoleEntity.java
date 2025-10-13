package com.example.data;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ruolo")
public class RoleEntity {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

     @Column(nullable = false, unique = true)
	    private String nome; // es: "USER", "ADMIN", "PERSONAL_TRAINER"

	    // Lato inverso della relazione ManyToMany
	    @ManyToMany(mappedBy = "ruoli")
	    private Set<UtenteEntity> utenti = new HashSet<>();

	    public RoleEntity() {}

	    public RoleEntity(String nome) {
	        this.nome = nome;
	    }

		public Object getNome() {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
