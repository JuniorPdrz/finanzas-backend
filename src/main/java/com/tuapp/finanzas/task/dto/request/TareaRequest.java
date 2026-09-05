package com.tuapp.finanzas.task.dto.request;

import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TareaRequest(
        @NotBlank(message = "El título es obligatorio") String titulo,
        String descripcion,
        @NotNull(message = "El proyectoId es obligatorio") Long proyectoId,
        Long asignadoA,
        PrioridadTarea prioridad
) {}