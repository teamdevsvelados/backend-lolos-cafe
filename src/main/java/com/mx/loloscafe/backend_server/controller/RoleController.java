package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.RoleNotFoundException;
import com.mx.loloscafe.backend_server.model.Role;
import com.mx.loloscafe.backend_server.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "*") // ajustar al puerto
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // GET /api/v1/roles
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Integer id) {
        try {
            Role role = roleService.findById(id);
            return ResponseEntity.ok(role); // 200
        } catch (RoleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    // GET /api/v1/roles/by-name/{name}
    @GetMapping("/by-name/{name}")
    public ResponseEntity<Role> getByName(@PathVariable String name) {
        try {
            Role role = roleService.findByNameOf(name);
            return ResponseEntity.ok(role); // 200
        } catch (RoleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    
    // POST /api/v1/roles
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role body, UriComponentsBuilder uriBuilder) {
        try {
            Role created = roleService.createRole(body);
            URI location = uriBuilder.path("/api/v1/roles/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
            return ResponseEntity.created(location).body(created); // 201 + Location
        } catch (IllegalStateException dup) { // nameOf duplicado
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        } catch (IllegalArgumentException bad) { // body inválido
            return ResponseEntity.badRequest().build(); // 400
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }


    // PUT /api/v1/roles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Integer id, @RequestBody Role body) {
        try {
            Role updated = roleService.updateRoleById(body, id);
            return ResponseEntity.ok(updated); // 200
        } catch (RoleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    
    // DELETE /api/v1/roles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            roleService.deleteRoleById(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (RoleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }
}