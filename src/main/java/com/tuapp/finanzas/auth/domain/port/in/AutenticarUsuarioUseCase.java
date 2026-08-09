package com.tuapp.finanzas.auth.domain.port.in;

import com.tuapp.finanzas.user.domain.model.Usuario;

import java.util.UUID;

public interface AutenticarUsuarioUseCase {
    ResultadoLogin login(String email, String passwordPlano);
    Usuario obtenerPorId(UUID id);

    record ResultadoLogin(String token, Usuario usuario) {}
}