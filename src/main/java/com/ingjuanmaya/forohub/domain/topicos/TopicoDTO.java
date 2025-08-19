package com.ingjuanmaya.forohub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public record TopicoDTO(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String id_curso,
        @NotNull
        String id_usuario
) {


    public TopicoDTO(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                String.valueOf(topico.getCurso().getId()),
                String.valueOf(topico.getUsuario().getId())
        );
    }

    // Conversión de un solo Topico -> TopicoDTO
    public static TopicoDTO convertOne(Topico topico) {
        return new TopicoDTO(
                topico.getTitulo(),
                topico.getMensaje(),
                String.valueOf(topico.getCurso().getId()),
                String.valueOf(topico.getUsuario().getId())
        );
    }

    // Conversión de una página completa de Topico -> TopicoDTO
    public static Page<TopicoDTO> convert(Page<Topico> topicos) {
        return topicos.map(TopicoDTO::convertOne);
    }
}
