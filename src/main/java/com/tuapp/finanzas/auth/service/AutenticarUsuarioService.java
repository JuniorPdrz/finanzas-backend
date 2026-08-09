package com.tuapp.finanzas.auth.service;

import com.tuapp.finanzas.domain.exception.CredencialesInvalidasException;
import com.tuapp.finanzas.domain.model.Usuario;
import com.tuapp.finanzas.domain.port.in.AutenticarUsuarioUseCase;
import com.tuapp.finanzas.domain.port.out.PasswordEncoderPort;
import com.tuapp.finanzas.domain.port.out.TokenPort;
import com.tuapp.finanzas.domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public ResultadoLogin login(String email, String passwordPlano) {
        Usuario usuario = repository.buscarPorEmail(email)
                .orElseThrow(CredencialesInvalidasException::new);

        if (!passwordEncoder.coincide(passwordPlano, usuario.getPasswordHash())) {
            throw new CredencialesInvalidasException();
        }

        String token = tokenPort.generarToken(usuario.getId(), usuario.getEmail());
        return new ResultadoLogin(token, usuario);
    }

    @Override
    public Usuario obtenerPorId(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }
}
