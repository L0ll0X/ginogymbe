package com.example.business;

import java.time.LocalDateTime;

import com.example.data.RoleEntity;
import com.example.data.UtenteEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

public class Utente_RoleService {

	@Entity
	@Table(name = "utente_ruolo")
	public class UtenteRuoloEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "utente_id", nullable = false)
	    private UtenteEntity utente;

	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "ruolo_id", nullable = false)
	    private RoleEntity ruolo;

	    
	    private LocalDateTime dataAssegnazione = LocalDateTime.now();

	   
	    public Long getId() { return id; }
	    public UtenteEntity getUtente() { return utente; }
	    public void setUtente(UtenteEntity utente) { this.utente = utente; }
	    public RoleEntity getRuolo() { return ruolo; }
	    public void setRuolo(RoleEntity ruolo) { this.ruolo = ruolo; }
	    public LocalDateTime getDataAssegnazione() { return dataAssegnazione; }
	    public void setDataAssegnazione(LocalDateTime dataAssegnazione) { this.dataAssegnazione = dataAssegnazione; }
	}
	
	
	
	
}
