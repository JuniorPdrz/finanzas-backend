package com.tuapp.finanzas.domain.port.in;

import com.tuapp.finanzas.domain.model.Usuario;

import java.util.UUID;

public interface AutenticarUsuarioUseCase {
    ResultadoLogin login(String email, String passwordPlano);
    Usuario obtenerPorId(UUID id);

    record ResultadoLogin(String token, Usuario usuario) {}
}
