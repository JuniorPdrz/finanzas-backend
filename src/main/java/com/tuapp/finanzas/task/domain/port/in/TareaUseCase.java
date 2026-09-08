package com.tuapp.finanzas.task.domain.port.in;

import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import com.tuapp.finanzas.task.domain.model.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;


public interface TareaUseCase {

    Tarea crear(String titulo, String descripcion, Long proyectoId, Long asignadoA,
                PrioridadTarea prioridad, Instant fechaInicio, Instant fechaFin);

    Tarea obtenerPorId(Long id);

    Page<Tarea> listar(Long proyectoId, Long asignadoA, EstadoTarea estado,
                       PrioridadTarea prioridad, Pageable pageable);

    Tarea actualizar(Long id, String nuevoTitulo, String nuevaDescripcion);

    void eliminar(Long id);

    Tarea cambiarEstado(Long id, EstadoTarea nuevoEstado);

    Tarea asignar(Long id, Long usuarioId);

    Tarea cambiarPrioridad(Long id, PrioridadTarea nuevaPrioridad);
}