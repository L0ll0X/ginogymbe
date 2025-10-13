package com.example.presentation.DTOs;


	import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

	@Data
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class GruppoMuscolareDTO {
	   
		private Long id;
	    private String nome;
	    private String descrizione;
	    
	
}
		 


