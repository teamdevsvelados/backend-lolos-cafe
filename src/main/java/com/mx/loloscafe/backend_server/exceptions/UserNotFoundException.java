package com.mx.loloscafe.backend_server.exceptions;


public class UserNotFoundException extends RuntimeException {
    //==========="Cuando un método se llama igual a la clase es el constructor"===========

    public UserNotFoundException(Integer id) {
        super("User not Found with Id: " + id);

    }
}
