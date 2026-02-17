package com.mx.loloscafe.backend_server.exceptions;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
