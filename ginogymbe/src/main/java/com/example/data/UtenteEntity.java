package com.example.data;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UtenteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;
	private  String nome;
	private String cognome;
	private String numeroDiCellulare;
	private String email;
	
	  @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "utente_ruolo",
	        joinColumns = @JoinColumn(name = "utente_id"),
	        inverseJoinColumns = @JoinColumn(name = "ruolo_id")
	    )
	    private Set<RoleEntity> ruoli;

	  public String getNome() {
		return nome;
	  }

	  public void setNome(String nome) {
		this.nome = nome;
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

	  public Long getId() {
		return id;
	  }
	  public void setId(Long Id) {
			this.id = Id;

        }
}
