package com.tuapp.finanzas.auth.service;

import com.tuapp.finanzas.auth.domain.exception.CredencialesInvalidasException;
import com.tuapp.finanzas.auth.domain.port.in.AutenticarUsuarioUseCase;
import com.tuapp.finanzas.auth.domain.port.out.TokenPort;
import com.tuapp.finanzas.user.domain.model.Usuario;
import com.tuapp.finanzas.user.domain.port.out.PasswordEncoderPort;
import com.tuapp.finanzas.user.domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuarioService implements AutenticarUsuarioUseCase {

    private final UsuarioRepositoryPort repository;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenPort tokenPort;

    public AutenticarUsuarioService(UsuarioRepositoryPort repository, PasswordEncoderPort passwordEncoder, TokenPort tokenPort) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenPort = tokenPort;
    }

    @Override
    public String login(String email, String passwordPlano) {
        Usuario usuario = repository.buscarPorEmail(email)
                .orElseThrow(CredencialesInvalidasException::new);

        if (!passwordEncoder.coincide(passwordPlano, usuario.getPasswordHash())) {
            throw new CredencialesInvalidasException();
        }

        return tokenPort.generarToken(usuario.getId(), usuario.getEmail());
    }
}
