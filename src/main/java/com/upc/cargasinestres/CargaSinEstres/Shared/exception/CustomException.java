package com.upc.cargasinestres.CargaSinEstres.Shared.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Class CustomException is a custom exception that extends RuntimeException
 * @author Grupo1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private HttpStatus status;

    public CustomException(HttpStatus status, String _message) {
        super(_message);
        this.status = status;
    }
}
