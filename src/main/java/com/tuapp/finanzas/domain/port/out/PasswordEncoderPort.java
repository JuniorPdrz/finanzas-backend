package com.tuapp.finanzas.domain.port.out;

public interface PasswordEncoderPort {
    String encriptar(String passwordPlano);
    boolean coincide(String passwordPlano, String passwordHash);
}