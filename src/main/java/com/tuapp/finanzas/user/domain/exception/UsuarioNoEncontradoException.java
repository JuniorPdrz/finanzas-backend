package com.tuapp.finanzas.user.domain.exception;

import java.util.UUID;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(UUID id) {
        super("Usuario no encontrado: " + id);
    }
}
