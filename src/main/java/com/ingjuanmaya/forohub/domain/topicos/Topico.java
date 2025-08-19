package com.ingjuanmaya.forohub.domain.topicos;


import com.ingjuanmaya.forohub.domain.cursos.Curso.Curso;
import com.ingjuanmaya.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Setter
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;
}

