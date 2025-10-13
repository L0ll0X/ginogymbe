package com.example.presentation;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.data.RoleEntity;
import com.example.data.UtenteEntity;
import com.example.data.repositories.RoleRepository;
import com.example.data.repositories.UtenteRepository;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private RoleRepository roleRepository;

    
    @GetMapping
    public List<UtenteEntity> getAllUtenti() {
        return utenteRepository.findAll();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<UtenteEntity> getUtenteById(@PathVariable Long id) {
        return utenteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<UtenteEntity> creaUtente(@RequestBody UtenteEntity nuovoUtente) {
        UtenteEntity salvato = utenteRepository.save(nuovoUtente);
        return ResponseEntity.ok(salvato);
    }

    
    @PostMapping("/{idUtente}/ruoli/{idRuolo}")
    public ResponseEntity<UtenteEntity> aggiungiRuoloAUtente(
            @PathVariable Long idUtente,
            @PathVariable Long idRuolo) {

        Optional<UtenteEntity> utenteOpt = utenteRepository.findById(idUtente);
        Optional<RoleEntity> ruoloOpt = roleRepository.findById(idRuolo);

        if (utenteOpt.isPresent() && ruoloOpt.isPresent()) {
            UtenteEntity utente = utenteOpt.get();
            RoleEntity ruolo = ruoloOpt.get();

         
            utenteRepository.save(utente);

            return ResponseEntity.ok(utente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaUtente(@PathVariable Long id) {
        if (utenteRepository.existsById(id)) {
            utenteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
