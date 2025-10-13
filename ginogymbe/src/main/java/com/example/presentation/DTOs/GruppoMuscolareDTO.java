package com.example.presentation.DTOs;


	import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

	@Data
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class GruppoMuscolareDTO {
	   
		private Long id;
	    private String nome;
	    private String descrizione;
	    
		public GruppoMuscolareDTO(Long id2, String nome2, String descrizione2) {
			this.id = id2;
			this.nome = nome2;
			this.descrizione = descrizione2;
		}
	
}
		 


