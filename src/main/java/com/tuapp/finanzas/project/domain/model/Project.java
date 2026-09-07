package com.tuapp.finanzas.project.domain.model;

import com.tuapp.finanzas.project.domain.exception.ProyectoInvalidoException;

import java.time.Instant;

public class Project {

    private final Long id;
    private String nombre;
    private String descripcion;
    private final Long teamId;
    private boolean activo;
    private final Instant fechaCreacion;
    private Instant fechaActualizacion;

    private Project(Long id, String nombre, String descripcion, Long teamId,
                    boolean activo, Instant fechaCreacion, Instant fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.teamId = teamId;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static Project crear(String nombre, String descripcion, Long teamId) {
        validarNombre(nombre);
        if (teamId == null) {
            throw new ProyectoInvalidoException("El proyecto debe pertenecer a un equipo");
        }
        Instant ahora = Instant.now();
        return new Project(null, nombre, descripcion, teamId, true, ahora, ahora);
    }

    public static Project reconstruir(Long id, String nombre, String descripcion, Long teamId,
                                      boolean activo, Instant fechaCreacion, Instant fechaActualizacion) {
        return new Project(id, nombre, descripcion, teamId, activo, fechaCreacion, fechaActualizacion);
    }

    public void actualizar(String nuevoNombre, String nuevaDescripcion) {
        validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        this.descripcion = nuevaDescripcion;
        this.fechaActualizacion = Instant.now();
    }

    public void archivar() {
        this.activo = false;
        this.fechaActualizacion = Instant.now();
    }

    public void activar() {
        this.activo = true;
        this.fechaActualizacion = Instant.now();
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ProyectoInvalidoException("El nombre del proyecto no puede estar vacío");
        }
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Long getTeamId() { return teamId; }
    public boolean isActivo() { return activo; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public Instant getFechaActualizacion() { return fechaActualizacion; }
}