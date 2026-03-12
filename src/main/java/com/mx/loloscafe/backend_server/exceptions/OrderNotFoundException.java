package com.mx.loloscafe.backend_server.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer idOrder) {

        super("Order not Found with Id: " + idOrder);
    }
}
