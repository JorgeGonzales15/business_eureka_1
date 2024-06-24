package com.upc.cargasinestres.CargaSinEstres.Shared.exception;

/**
 * This exception is thrown when a resource is not found.
 * @author Grupo1
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
