package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.PermissionNotFoundException;
import com.mx.loloscafe.backend_server.model.Permission;
import com.mx.loloscafe.backend_server.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@CrossOrigin(origins = "*") // ajustar al puerto
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    
    // GET /api/v1/permissions
    @GetMapping
    public ResponseEntity<List<Permission>> list() {
        try {
            List<Permission> permissions = permissionService.getPermissions();
            return ResponseEntity.ok(permissions); // 200
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // GET /api/v1/permissions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getById(@PathVariable Integer id) {
        try {
            Permission p = permissionService.findById(id);
            return ResponseEntity.ok(p); // 200
        } catch (PermissionNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // POST /api/v1/permissions
    @PostMapping
    public ResponseEntity<Permission> create(@RequestBody Permission body, UriComponentsBuilder uriBuilder) {
        try {
            Permission created = permissionService.createPermission(body);
            URI location = uriBuilder.path("/api/v1/permissions/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
            return ResponseEntity.created(location).body(created); // 201 + Location
        } catch (IllegalStateException dup) { // nameOf duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        } catch (IllegalArgumentException bad) { // body inválido, si lo usas
            return ResponseEntity.badRequest().build(); // 400
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    
    // PUT /api/v1/permissions/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Permission> update(@PathVariable Integer id, @RequestBody Permission body) {
        try {
            Permission updated = permissionService.updatePermissionById(body, id);
            return ResponseEntity.ok(updated); // 200
        } catch (PermissionNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (IllegalArgumentException bad) {
            return ResponseEntity.badRequest().build(); // 400
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }


    
    // DELETE /api/v1/permissions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            permissionService.deletePermissionById(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (PermissionNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

}