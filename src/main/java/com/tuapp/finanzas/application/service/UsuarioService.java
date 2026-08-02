package com.tuapp.finanzas.application.service;

import com.tuapp.finanzas.domain.exception.UsuarioNoEncontradoException;
import com.tuapp.finanzas.domain.model.Usuario;
import com.tuapp.finanzas.domain.port.in.UsuarioUseCase;
import com.tuapp.finanzas.domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort repository;

    public UsuarioService(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Usuario crear(String nombre, String email, String passwordHash) {
        repository.buscarPorEmail(email).ifPresent(u -> {
            throw new IllegalStateException("Ya existe un usuario con ese email");
        });
        Usuario usuario = Usuario.crear(nombre, email, passwordHash);
        return repository.guardar(usuario);
    }

    @Override
    public Usuario obtenerPorId(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.listarTodos();
    }

    @Override
    public Usuario actualizarNombre(UUID id, String nuevoNombre) {
        Usuario usuario = obtenerPorId(id);
        usuario.actualizarNombre(nuevoNombre);
        return repository.guardar(usuario);
    }

    @Override
    public void eliminar(UUID id) {
        obtenerPorId(id);
        repository.eliminar(id);
    }
}