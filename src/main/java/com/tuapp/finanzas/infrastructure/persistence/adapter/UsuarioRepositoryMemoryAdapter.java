package com.tuapp.finanzas.infrastructure.persistence.adapter;

import com.tuapp.finanzas.domain.model.Usuario;
import com.tuapp.finanzas.domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UsuarioRepositoryMemoryAdapter implements UsuarioRepositoryPort {

    private final Map<UUID, Usuario> almacenamiento = new ConcurrentHashMap<>();

    @Override
    public Usuario guardar(Usuario usuario) {
        almacenamiento.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return Optional.ofNullable(almacenamiento.get(id));
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return almacenamiento.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Usuario> listarTodos() {
        return List.copyOf(almacenamiento.values());
    }

    @Override
    public void eliminar(UUID id) {
        almacenamiento.remove(id);
    }
}