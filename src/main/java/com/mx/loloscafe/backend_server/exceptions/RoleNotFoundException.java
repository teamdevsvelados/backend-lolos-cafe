package com.mx.loloscafe.backend_server.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Integer id) {
        super("Role not found with id: " + id);
    }
    public RoleNotFoundException(String nameOf) {
        super("Role not found with nameOf: " + nameOf);
    }
}