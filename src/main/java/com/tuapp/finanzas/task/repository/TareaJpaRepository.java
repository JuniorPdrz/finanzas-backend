package com.tuapp.finanzas.task.repository;

import com.tuapp.finanzas.task.domain.model.EstadoTarea;
import com.tuapp.finanzas.task.domain.model.PrioridadTarea;
import com.tuapp.finanzas.task.entity.TareaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TareaJpaRepository extends JpaRepository<TareaEntity, Long> {

    @Query("""
        SELECT t FROM TareaEntity t
        WHERE (:proyectoId IS NULL OR t.proyectoId = :proyectoId)
        AND (:asignadoA IS NULL OR t.asignadoA = :asignadoA)
        AND (:estado IS NULL OR t.estado = :estado)
        AND (:prioridad IS NULL OR t.prioridad = :prioridad)
        """)
    Page<TareaEntity> buscarConFiltros(@Param("proyectoId") Long proyectoId,
                                       @Param("asignadoA") Long asignadoA,
                                       @Param("estado") EstadoTarea estado,
                                       @Param("prioridad") PrioridadTarea prioridad,
                                       Pageable pageable);

    long countByProyectoIdAndEstado(Long proyectoId, EstadoTarea estado);

    long countByProyectoId(Long proyectoId);
}