package com.mx.loloscafe.backend_server.exceptions;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(Integer id) {
        super("Permission not found with id: " + id);
    }
    public PermissionNotFoundException(String nameOf) {
        super("Permission not found with nameOf: " + nameOf);
    }
}