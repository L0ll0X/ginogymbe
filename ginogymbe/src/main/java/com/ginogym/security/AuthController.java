package com.ginogym.security;

import com.ginogym.business.DTOs.UserDTO;
import com.ginogym.data.entities.Role;
import com.ginogym.data.entities.User;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationManager authManager;
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO dto) {
        if(customUserDetailsService.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body("Email già in uso");
        }

        // Mappa DTO -> Entity
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCellulare(dto.getCellulare());

        // Trasforma nomi dei ruoli in oggetti Role
        if(dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            Set<Role> roles = dto.getRoles().stream()
                .map(roleName -> customUserDetailsService.findRoleByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Ruolo non trovato: " + roleName)))
                .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        customUserDetailsService.save(user);
        return ResponseEntity.ok(user);
    }

    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody User user) {
    //     if(customUserDetailsService.existsByEmail(user.getEmail())) {
    //     return ResponseEntity.badRequest().body("Email già in uso");
    //     }
    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     customUserDetailsService.save(user);
    //     return ResponseEntity.ok(user);
    // }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String newHash = encoder.encode("admin123");
            System.out.println(newHash);
            // Passa direttamente username/email e password in chiaro
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsernameOrEmail(),
                            request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            // Ottieni i dati dell'utente dall'Authentication appena autenticata
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsernameOrEmail());
            String jwt = jwtUtils.generateToken(userDetails.getUsername(),
                    userDetails.getAuthorities().stream()
                            .map(a -> a.getAuthority().replace("ROLE_", ""))
                            .collect(Collectors.toSet()));

            return ResponseEntity.ok(jwt);

        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali non valide");
        }
    }

}
