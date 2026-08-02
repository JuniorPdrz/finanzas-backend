package com.tuapp.finanzas.domain.exception;

import java.util.UUID;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(UUID id) {
        super("Usuario no encontrado: " + id);
    }
}