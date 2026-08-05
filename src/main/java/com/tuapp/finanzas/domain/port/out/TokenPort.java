package com.tuapp.finanzas.domain.port.out;

import java.util.UUID;

public interface TokenPort {
    String generarToken(UUID usuarioId, String email);
    UUID validarYObtenerUsuarioId(String token);
}