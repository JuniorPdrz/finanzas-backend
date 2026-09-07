package com.tuapp.finanzas.project.domain.port.out;

import com.tuapp.finanzas.project.domain.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectRepositoryPort {
    Project save(Project proyecto);
    Optional<Project> findById(Long id);
    Page<Project> findByFiltros(Long teamId, Boolean activo, Pageable pageable);
    void deleteById(Long id);
    boolean existsById(Long id);
}