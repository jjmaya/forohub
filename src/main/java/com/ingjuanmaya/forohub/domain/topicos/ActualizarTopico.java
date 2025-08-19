package com.ingjuanmaya.forohub.domain.topicos;

import org.springframework.stereotype.Service;


public record ActualizarTopico(String titulo, String mensaje) {
    public Topico actualizar(Long id, TopicoRepositoy topicoRepositoy) {
        Topico topico = topicoRepositoy.getReferenceById(id);
        if (this.titulo != null) topico.setTitulo(this.titulo);
        if (this.mensaje != null) topico.setMensaje(this.mensaje);
        return topico;
    }
}

