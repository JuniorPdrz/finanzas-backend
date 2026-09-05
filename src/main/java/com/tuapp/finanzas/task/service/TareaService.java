package com.tuapp.finanzas.task.service;

import com.tuapp.finanzas.task.domain.exception.TareaNoEncontradaException;
import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import com.tuapp.finanzas.task.domain.model.Tarea;
import com.tuapp.finanzas.task.domain.port.in.TareaUseCase;
import com.tuapp.finanzas.task.domain.port.out.TareaRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TareaService implements TareaUseCase {

    private final TareaRepositoryPort tareaRepositoryPort;

    public TareaService(TareaRepositoryPort tareaRepositoryPort) {
        this.tareaRepositoryPort = tareaRepositoryPort;
    }

    @Override
    @Transactional
    public Tarea crear(String titulo, String descripcion, Long proyectoId,
                       Long asignadoA, PrioridadTarea prioridad) {
        Tarea tarea = Tarea.crear(titulo, descripcion, proyectoId, asignadoA, prioridad);
        return tareaRepositoryPort.save(tarea);
    }

    @Override
    public Tarea obtenerPorId(Long id) {
        return tareaRepositoryPort.findById(id)
                .orElseThrow(() -> new TareaNoEncontradaException(id));
    }

    @Override
    public Page<Tarea> listar(Long proyectoId, Long asignadoA, EstadoTarea estado,
                              PrioridadTarea prioridad, Pageable pageable) {
        return tareaRepositoryPort.findByFiltros(proyectoId, asignadoA, estado, prioridad, pageable);
    }

    @Override
    @Transactional
    public Tarea actualizar(Long id, String nuevoTitulo, String nuevaDescripcion) {
        Tarea tarea = obtenerPorId(id);
        tarea.actualizar(nuevoTitulo, nuevaDescripcion);
        return tareaRepositoryPort.save(tarea);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!tareaRepositoryPort.existsById(id)) {
            throw new TareaNoEncontradaException(id);
        }
        tareaRepositoryPort.deleteById(id);
    }

    @Override
    @Transactional
    public Tarea cambiarEstado(Long id, EstadoTarea nuevoEstado) {
        Tarea tarea = obtenerPorId(id);
        tarea.cambiarEstado(nuevoEstado);
        return tareaRepositoryPort.save(tarea);
    }

    @Override
    @Transactional
    public Tarea asignar(Long id, Long usuarioId) {
        Tarea tarea = obtenerPorId(id);
        tarea.asignar(usuarioId);
        return tareaRepositoryPort.save(tarea);
    }

    @Override
    @Transactional
    public Tarea cambiarPrioridad(Long id, PrioridadTarea nuevaPrioridad) {
        Tarea tarea = obtenerPorId(id);
        tarea.cambiarPrioridad(nuevaPrioridad);
        return tareaRepositoryPort.save(tarea);
    }
}