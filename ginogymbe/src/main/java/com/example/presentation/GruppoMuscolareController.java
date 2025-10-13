package com.example.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.GruppoMuscolareService;
import com.example.data.GruppoMuscolareEntity;
import com.example.presentation.DTOs.GruppoMuscolareDTO;
import com.example.presentation.Requests.CreateGruppoMuscolareRequest;
import com.example.presentation.Requests.PageRequests;

import lombok.RequiredArgsConstructor;

@RestController //segue i protocolli http
@RequestMapping("/gruppi-muscolari")
@RequiredArgsConstructor
public class GruppoMuscolareController {

	@Autowired //DI o con costruttore
    private final GruppoMuscolareService service;

    @GetMapping
    public List<GruppoMuscolareDTO> getAll(@RequestParam PageRequests request) {
        return service.getAll(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GruppoMuscolareDTO> getById(@PathVariable Long id) {
    	//aggiungere controlli formali
    	GruppoMuscolareDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
        
    }

    @PostMapping
    public GruppoMuscolareDTO create(@RequestBody CreateGruppoMuscolareRequest request) {
    	
        //GruppoMuscolareEntity saved = service.save(request);
       // return GruppoMuscolareDTO.builder().id(saved.getId()).nome(saved.getNome()).descrizione(saved.getDescrizione()).build();
    	return new GruppoMuscolareDTO();
    }
    
    //piumapping? ???? dov'è

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
