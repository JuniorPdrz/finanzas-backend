package com.tuapp.finanzas.task.domain.port.out;

import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import com.tuapp.finanzas.task.domain.model.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TareaRepositoryPort {
    Tarea save(Tarea tarea);
    Optional<Tarea> findById(Long id);
    Page<Tarea> findByFiltros(Long proyectoId, Long asignadoA, EstadoTarea estado,
                              PrioridadTarea prioridad, Pageable pageable);
    void deleteById(Long id);
    boolean existsById(Long id);
    long countByProyectoId(Long proyectoId);
    long countByProyectoIdAndEstado(Long proyectoId, EstadoTarea estado);
    List<Tarea> findByRangoFechas(Instant start, Instant end, Long proyectoId, Long usuarioId);
}