package com.tuapp.finanzas.task.controller;

import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import com.tuapp.finanzas.task.domain.model.Tarea;
import com.tuapp.finanzas.task.domain.port.in.TareaUseCase;
import com.tuapp.finanzas.task.dto.request.*;
import com.tuapp.finanzas.task.dto.response.TareaResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TareaController {

    private final TareaUseCase tareaUseCase;

    public TareaController(TareaUseCase tareaUseCase) {
        this.tareaUseCase = tareaUseCase;
    }

    @GetMapping
    public ResponseEntity<Page<TareaResponse>> listar(
            @RequestParam(required = false) Long proyectoId,
            @RequestParam(required = false) Long asignadoA,
            @RequestParam(required = false) EstadoTarea estado,
            @RequestParam(required = false) PrioridadTarea prioridad,
            Pageable pageable) {
        Page<TareaResponse> pagina = tareaUseCase.listar(proyectoId, asignadoA, estado, prioridad, pageable)
                .map(this::toResponse);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    public ResponseEntity<TareaResponse> crear(@Valid @RequestBody TareaRequest request) {
        Tarea tarea = tareaUseCase.crear(request.titulo(), request.descripcion(), request.proyectoId(),
                request.asignadoA(), request.prioridad(), request.fechaInicio(), request.fechaFin());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(tarea));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(tareaUseCase.obtenerPorId(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TareaResponse> actualizar(@PathVariable Long id,
                                                    @RequestBody TareaActualizarRequest request) {
        return ResponseEntity.ok(toResponse(tareaUseCase.actualizar(id, request.titulo(), request.descripcion())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaUseCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TareaResponse> cambiarEstado(@PathVariable Long id,
                                                       @Valid @RequestBody EstadoRequest request) {
        return ResponseEntity.ok(toResponse(tareaUseCase.cambiarEstado(id, request.estado())));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<TareaResponse> asignar(@PathVariable Long id,
                                                 @Valid @RequestBody AsignarRequest request) {
        return ResponseEntity.ok(toResponse(tareaUseCase.asignar(id, request.usuarioId())));
    }

    @PatchMapping("/{id}/priority")
    public ResponseEntity<TareaResponse> cambiarPrioridad(@PathVariable Long id,
                                                          @Valid @RequestBody PrioridadRequest request) {
        return ResponseEntity.ok(toResponse(tareaUseCase.cambiarPrioridad(id, request.prioridad())));
    }

    private TareaResponse toResponse(Tarea t) {
        return new TareaResponse(t.getId(), t.getTitulo(), t.getDescripcion(), t.getProyectoId(),
                t.getAsignadoA(), t.getEstado(), t.getPrioridad(),
                t.getFechaInicio(), t.getFechaFin(),
                t.getFechaCreacion(), t.getFechaActualizacion());
    }
}