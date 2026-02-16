package com.mx.loloscafe.backend_server.exceptions;

public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(Integer id) {
        super("Oferta no encontrada con ID: " + id);
    }
}
