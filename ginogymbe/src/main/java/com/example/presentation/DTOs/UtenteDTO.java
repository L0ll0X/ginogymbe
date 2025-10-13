package com.example.presentation.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {
	   
 
	private Long id;
    private String nome;
    private String cognome;
    private String numeroDiCellulare;
    private String email;
    
    
    public UtenteDTO(Long id, String nome2, String cognome2, String numeroDiCellulare2, String email2) {
 		this.setId(id);
 		this.setNome(nome2);
 		this.setCognome(cognome2);
 		this.setNumeroDiCellulare(numeroDiCellulare2);
 		this.setEmail(email2);
 	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getNumeroDiCellulare() {
		return numeroDiCellulare;
	}


	public void setNumeroDiCellulare(String numeroDiCellulare) {
		this.numeroDiCellulare = numeroDiCellulare;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}    
}
