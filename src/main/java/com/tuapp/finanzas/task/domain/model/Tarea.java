package com.tuapp.finanzas.task.domain.model;

import com.tuapp.finanzas.task.domain.exception.TareaInvalidaException;

import java.time.Instant;

public class Tarea {

    private final Long id;
    private String titulo;
    private String descripcion;
    private final Long proyectoId;
    private Long asignadoA;
    private EstadoTarea estado;
    private PrioridadTarea prioridad;
    private final Instant fechaCreacion;
    private Instant fechaActualizacion;

    private Tarea(Long id, String titulo, String descripcion, Long proyectoId, Long asignadoA,
                  EstadoTarea estado, PrioridadTarea prioridad,
                  Instant fechaCreacion, Instant fechaActualizacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proyectoId = proyectoId;
        this.asignadoA = asignadoA;
        this.estado = estado;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static Tarea crear(String titulo, String descripcion, Long proyectoId,
                              Long asignadoA, PrioridadTarea prioridad) {
        validarTitulo(titulo);
        if (proyectoId == null) {
            throw new TareaInvalidaException("La tarea debe pertenecer a un proyecto");
        }
        Instant ahora = Instant.now();
        PrioridadTarea prioridadFinal = prioridad != null ? prioridad : PrioridadTarea.MEDIA;
        return new Tarea(null, titulo, descripcion, proyectoId, asignadoA,
                EstadoTarea.BACKLOG, prioridadFinal, ahora, ahora);
    }

    public static Tarea reconstruir(Long id, String titulo, String descripcion, Long proyectoId,
                                    Long asignadoA, EstadoTarea estado, PrioridadTarea prioridad,
                                    Instant fechaCreacion, Instant fechaActualizacion) {
        return new Tarea(id, titulo, descripcion, proyectoId, asignadoA, estado, prioridad,
                fechaCreacion, fechaActualizacion);
    }

    public void actualizar(String nuevoTitulo, String nuevaDescripcion) {
        validarTitulo(nuevoTitulo);
        this.titulo = nuevoTitulo;
        this.descripcion = nuevaDescripcion;
        this.fechaActualizacion = Instant.now();
    }

    public void cambiarEstado(EstadoTarea nuevoEstado) {
        if (nuevoEstado == null) {
            throw new TareaInvalidaException("El estado no puede ser nulo");
        }
        this.estado = nuevoEstado;
        this.fechaActualizacion = Instant.now();
    }

    public void asignar(Long usuarioId) {
        this.asignadoA = usuarioId;
        this.fechaActualizacion = Instant.now();
    }

    public void cambiarPrioridad(PrioridadTarea nuevaPrioridad) {
        if (nuevaPrioridad == null) {
            throw new TareaInvalidaException("La prioridad no puede ser nula");
        }
        this.prioridad = nuevaPrioridad;
        this.fechaActualizacion = Instant.now();
    }

    private static void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new TareaInvalidaException("El título de la tarea no puede estar vacío");
        }
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public Long getProyectoId() { return proyectoId; }
    public Long getAsignadoA() { return asignadoA; }
    public EstadoTarea getEstado() { return estado; }
    public PrioridadTarea getPrioridad() { return prioridad; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public Instant getFechaActualizacion() { return fechaActualizacion; }
}