package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.model.Role;
import com.mx.loloscafe.backend_server.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "*")//ajustar al puerto
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // GET /api/roles
    @GetMapping
    public List<Role> list() {
        return roleService.getRoles();
    }

    // GET /api/roles/{id}
    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    // GET /api/roles/by-name/{name}
    @GetMapping("/by-name/{name}")
    public Role getByName(@PathVariable String name) {
        return roleService.findByNameOf(name);
    }

    // POST /api/roles
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role body) {
        Role created = roleService.createRole(body);
        return ResponseEntity.created(URI.create("/api/roles/" + created.getId()))
                .body(created);
    }

    // PUT /api/roles/{id}
    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role body) {
        return roleService.updateRoleById(body, id);
    }

    // DELETE /api/roles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}