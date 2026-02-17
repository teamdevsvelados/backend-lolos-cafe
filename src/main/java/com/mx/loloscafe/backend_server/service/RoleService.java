package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.RoleNotFoundException;
import com.mx.loloscafe.backend_server.model.Role;
import com.mx.loloscafe.backend_server.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    // Inyección por constructor
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Listar
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    // Crear
    public Role createRole(Role newRole) {
        String name = newRole.getNameOf();
        if (roleRepository.existsByNameOf(name)) {
            throw new IllegalStateException("Role already exists with nameOf: " + name);
        }
        return roleRepository.save(newRole);
    }

    // Buscar por id o excepción
    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    //  Buscar por nombre
    public Role findByNameOf(String nameOf) {
        Role r = roleRepository.findByNameOf(nameOf);
        if (r == null) throw new RoleNotFoundException(nameOf);
        return r;
    }

    // Borrar
    public void deleteRoleById(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException(id);
        }
    }

    // Actualizar (solo campos editables)
    public Role updateRoleById(Role role, Integer id) {
        return roleRepository.findById(id)
                .map(db -> {
                    db.setNameOf(role.getNameOf());
                    return roleRepository.save(db);
                })
                .orElseThrow(() -> new RoleNotFoundException(id));
    }
}