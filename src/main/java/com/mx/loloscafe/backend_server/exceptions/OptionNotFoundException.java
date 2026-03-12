package com.mx.loloscafe.backend_server.exceptions;

public class OptionNotFoundException extends RuntimeException {
    public OptionNotFoundException(Integer id) {
        super("Option not found with id: " + id);
    }
}
