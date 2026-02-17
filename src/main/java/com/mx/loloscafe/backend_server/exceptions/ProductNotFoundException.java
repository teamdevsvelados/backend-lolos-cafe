package com.mx.loloscafe.backend_server.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super("Product not Found with Id: " + id);
    }
}
