package com.tuapp.finanzas.task.adapter;

import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import com.tuapp.finanzas.task.domain.model.Tarea;
import com.tuapp.finanzas.task.domain.port.out.TareaRepositoryPort;
import com.tuapp.finanzas.task.repository.TareaJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TareaRepositoryJpaAdapter implements TareaRepositoryPort {

    private final TareaJpaRepository jpaRepository;
    private final TareaEntityMapper mapper;

    public TareaRepositoryJpaAdapter(TareaJpaRepository jpaRepository, TareaEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Tarea save(Tarea tarea) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(tarea)));
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<Tarea> findByFiltros(Long proyectoId, Long asignadoA, EstadoTarea estado,
                                     PrioridadTarea prioridad, Pageable pageable) {
        return jpaRepository.buscarConFiltros(proyectoId, asignadoA, estado, prioridad, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public long countByProyectoId(Long proyectoId) {
        return jpaRepository.countByProyectoId(proyectoId);
    }

    @Override
    public long countByProyectoIdAndEstado(Long proyectoId, EstadoTarea estado) {
        return jpaRepository.countByProyectoIdAndEstado(proyectoId, estado);
    }
}