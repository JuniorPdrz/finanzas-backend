package com.tuapp.finanzas.auth.domain.port.out;

public interface TokenPort {
    String generarToken(Long usuarioId, String email);
    Long validarYObtenerUsuarioId(String token);
}