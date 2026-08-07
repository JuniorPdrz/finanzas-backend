package com.tuapp.finanzas.user.domain.port.out;

import com.tuapp.finanzas.user.domain.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(UUID id);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarTodos();
    void eliminar(UUID id);
}
