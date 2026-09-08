package com.tuapp.finanzas.task.dto.response;

import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;

import java.time.Instant;

public record TareaResponse(
        Long id,
        String titulo,
        String descripcion,
        Long proyectoId,
        Long asignadoA,
        EstadoTarea estado,
        PrioridadTarea prioridad,
        Instant fechaInicio,
        Instant fechaFin,
        Instant fechaCreacion,
        Instant fechaActualizacion
) {}