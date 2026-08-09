package com.tuapp.finanzas.domain.port.in;

public interface AutenticarUsuarioUseCase {
    ResultadoLogin login(String email, String passwordPlano);
    Usuario obtenerPorId(UUID id);

    record ResultadoLogin(String token, Usuario usuario) {}
}
