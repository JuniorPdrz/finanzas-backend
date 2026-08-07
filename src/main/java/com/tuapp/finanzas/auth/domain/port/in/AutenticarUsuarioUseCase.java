package com.tuapp.finanzas.auth.domain.port.in;

public interface AutenticarUsuarioUseCase {
    String login(String email, String passwordPlano);
}
