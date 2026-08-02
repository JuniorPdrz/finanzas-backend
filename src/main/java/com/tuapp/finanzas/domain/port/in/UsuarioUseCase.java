package com.tuapp.finanzas.domain.port.in;

import com.tuapp.finanzas.domain.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioUseCase {
    Usuario crear(String nombre, String email, String passwordHash);
    Usuario obtenerPorId(UUID id);
    List<Usuario> listarTodos();
    Usuario actualizarNombre(UUID id, String nuevoNombre);
    void eliminar(UUID id);
}