package com.ingjuanmaya.forohub.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepositoy extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable paginacion);

    Page<Topico> findByNombreCurso(String nombreCurso, Pageable paginacion);

    Optional<Topico> findById(Long id);

}
