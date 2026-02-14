package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.PermissionNotFoundException;
import com.mx.loloscafe.backend_server.model.Permission;
import com.mx.loloscafe.backend_server.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    // Inyección por constructor
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    // Listar todos
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    // Crear
    public Permission createPermission(Permission newPermission) {
        return permissionRepository.save(newPermission);
    }

    // Buscar por id o lanzar excepción
    public Permission findById(Integer id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }

    // Borrar por id
    public void deletePermissionById(Integer id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
        } else {
            throw new PermissionNotFoundException(id);
        }
    }

    // Actualizar por id (solo campos editables)
    public Permission updatePermissionById(Permission permission, Integer id) {
        return permissionRepository.findById(id)
                .map(db -> {
                    db.setNameOf(permission.getNameOf());
                    return permissionRepository.save(db);
                })
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }
}