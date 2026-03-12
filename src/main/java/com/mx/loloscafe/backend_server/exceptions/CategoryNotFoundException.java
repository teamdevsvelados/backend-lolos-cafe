package com.mx.loloscafe.backend_server.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    // Thrown when a category ID does not exist in the database
    public CategoryNotFoundException(Integer id) {
        super("Category not Found with Id: " + id);
    }
}
