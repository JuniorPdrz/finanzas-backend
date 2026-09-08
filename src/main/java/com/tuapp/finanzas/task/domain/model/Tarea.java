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
    private Instant fechaInicio;
    private Instant fechaFin;
    private final Instant fechaCreacion;
    private Instant fechaActualizacion;

    private Tarea(Long id, String titulo, String descripcion, Long proyectoId, Long asignadoA,
                  EstadoTarea estado, PrioridadTarea prioridad,
                  Instant fechaInicio, Instant fechaFin,
                  Instant fechaCreacion, Instant fechaActualizacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proyectoId = proyectoId;
        this.asignadoA = asignadoA;
        this.estado = estado;
        this.prioridad = prioridad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static Tarea crear(String titulo, String descripcion, Long proyectoId,
                              Long asignadoA, PrioridadTarea prioridad,
                              Instant fechaInicio, Instant fechaFin) {
        validarTitulo(titulo);
        if (proyectoId == null) {
            throw new TareaInvalidaException("La tarea debe pertenecer a un proyecto");
        }
        validarFechas(fechaInicio, fechaFin);
        Instant ahora = Instant.now();
        PrioridadTarea prioridadFinal = prioridad != null ? prioridad : PrioridadTarea.MEDIA;
        return new Tarea(null, titulo, descripcion, proyectoId, asignadoA,
                EstadoTarea.BACKLOG, prioridadFinal, fechaInicio, fechaFin, ahora, ahora);
    }

    public static Tarea reconstruir(Long id, String titulo, String descripcion, Long proyectoId,
                                    Long asignadoA, EstadoTarea estado, PrioridadTarea prioridad,
                                    Instant fechaInicio, Instant fechaFin,
                                    Instant fechaCreacion, Instant fechaActualizacion) {
        return new Tarea(id, titulo, descripcion, proyectoId, asignadoA, estado, prioridad,
                fechaInicio, fechaFin, fechaCreacion, fechaActualizacion);
    }

    public void actualizar(String nuevoTitulo, String nuevaDescripcion) {
        validarTitulo(nuevoTitulo);
        this.titulo = nuevoTitulo;
        this.descripcion = nuevaDescripcion;
        this.fechaActualizacion = Instant.now();
    }

    public void reprogramar(Instant nuevaFechaInicio, Instant nuevaFechaFin) {
        validarFechas(nuevaFechaInicio, nuevaFechaFin);
        this.fechaInicio = nuevaFechaInicio;
        this.fechaFin = nuevaFechaFin;
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

    private static void validarFechas(Instant fechaInicio, Instant fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new TareaInvalidaException("La tarea debe tener fecha de inicio y de fin");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new TareaInvalidaException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public Long getProyectoId() { return proyectoId; }
    public Long getAsignadoA() { return asignadoA; }
    public EstadoTarea getEstado() { return estado; }
    public PrioridadTarea getPrioridad() { return prioridad; }
    public Instant getFechaInicio() { return fechaInicio; }
    public Instant getFechaFin() { return fechaFin; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public Instant getFechaActualizacion() { return fechaActualizacion; }
}