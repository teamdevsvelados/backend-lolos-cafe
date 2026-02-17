package com.mx.loloscafe.backend_server.exceptions;

public class SizeNotFoundException extends RuntimeException {

    public SizeNotFoundException(Integer id){
        super("Size not found with Id: " + id);
    }
}
