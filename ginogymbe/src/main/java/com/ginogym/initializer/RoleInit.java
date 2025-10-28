package com.ginogym.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.ginogym.data.entities.Role;
import com.ginogym.data.repositories.RoleRepository;
import com.ginogym.enumeration.RoleEnum;
;


@Component
@Order(1)
public class RoleInit implements CommandLineRunner{

    private final RoleRepository roleRepository;

    public RoleInit(RoleRepository roleRepository) { 
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                Role entity = new Role();
                    entity.setName(roleEnum.name()); // usa name() perché è un enum
                roleRepository.save(entity);
            }
        }
    }

}
