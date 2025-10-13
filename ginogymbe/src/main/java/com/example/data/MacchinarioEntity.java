package com.example.data;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="macchinario")
public class MacchinarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Nonnull()
	public String nome;
	   public MacchinarioEntity() {}

	    // ✅ Getter e Setter
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) { // <-- questo serve per il PUT
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }
	

}
