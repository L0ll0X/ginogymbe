package com.ginogym.business;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ginogym.data.entities.Cliente;
import com.ginogym.data.repositories.ClienteRepository;

@Repository
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getTuttiIClienti() {
        return clienteRepository.findAll();
    }
}


