package com.ingjuanmaya.forohub.domain.cursos.Curso;

import com.ingjuanmaya.forohub.domain.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findById(Long id);
}
