package com.tuapp.finanzas.domain.port.in;

public interface AutenticarUsuarioUseCase {
    String login(String email, String passwordPlano);
}