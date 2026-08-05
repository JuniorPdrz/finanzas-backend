package com.tuapp.finanzas.infrastructure.persistence.adapter;

import com.tuapp.finanzas.domain.model.Usuario;
import com.tuapp.finanzas.domain.port.out.UsuarioRepositoryPort;
import com.tuapp.finanzas.infrastructure.persistence.UsuarioEntityMapper;
import com.tuapp.finanzas.infrastructure.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioRepositoryJpaAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioEntityMapper mapper;

    public UsuarioRepositoryJpaAdapter(UsuarioJpaRepository jpaRepository, UsuarioEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void eliminar(UUID id) {
        jpaRepository.deleteById(id);
    }
}