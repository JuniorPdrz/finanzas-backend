package com.tuapp.finanzas.domain.exception;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException() {
        super("Email o contraseña incorrectos");
    }
}