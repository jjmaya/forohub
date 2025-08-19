package com.ingjuanmaya.forohub.domain.topicos;


import com.ingjuanmaya.forohub.domain.cursos.Curso.Curso;
import com.ingjuanmaya.forohub.domain.cursos.Curso.CursoRepository;
import com.ingjuanmaya.forohub.domain.usuarios.Usuario;
import com.ingjuanmaya.forohub.domain.usuarios.UsuarioRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistrarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long idCurso,
        @NotNull Long idUsuario
) {
    public Topico convert(CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));


        return new Topico(
                null, // ID autogenerado
                titulo,
                mensaje,
                curso,
                usuario,
                LocalDateTime.now(),
                StatusTopico.ABIERTO // un valor inicial por defecto
        );
    }
}
