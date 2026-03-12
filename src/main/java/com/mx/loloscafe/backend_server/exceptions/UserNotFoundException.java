package com.mx.loloscafe.backend_server.exceptions;

public class UserNotFoundException extends RuntimeException {

    // Cuando no se encuentra por EMAIL
    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }

    // Cuando no se encuentra por ID
    public UserNotFoundException(Integer id) {
        super("User not found with id: " + id);
    }
}
