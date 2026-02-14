package com.mx.loloscafe.backend_server.exceptions;

public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(Integer id) {
        super("Offer not Found with Id: " + id);
    }
}
