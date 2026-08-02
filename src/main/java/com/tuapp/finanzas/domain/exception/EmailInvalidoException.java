package com.tuapp.finanzas.domain.exception;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException(String mensaje) {
        super(mensaje);
    }
}