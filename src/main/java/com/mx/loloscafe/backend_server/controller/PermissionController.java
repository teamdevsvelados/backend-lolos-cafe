package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.model.Permission;
import com.mx.loloscafe.backend_server.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // GET /api/permissions
    @GetMapping
    public List<Permission> list() {
        return permissionService.getPermissions();
    }

    // GET /api/permissions/{id}
    @GetMapping("/{id}")
    public Permission getById(@PathVariable Integer id) {
        return permissionService.findById(id);
    }

    // POST /api/permissions
    @PostMapping
    public ResponseEntity<Permission> create(@RequestBody Permission body) {
        Permission created = permissionService.createPermission(body);
        URI location = URI.create("/api/permissions/" + created.getId());
        return ResponseEntity.created(location).body(created); // 201 + Location
    }

    // PUT /api/permissions/{id}
    @PutMapping("/{id}")
    public Permission update(@PathVariable Integer id, @RequestBody Permission body) {
        return permissionService.updatePermissionById(body, id);
    }

    // DELETE /api/permissions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        permissionService.deletePermissionById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}