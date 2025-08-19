create table topicos (
    id bigint generated always as identity primary key,
    titulo varchar(100) not null,
    mensaje varchar(300) not null,
    id_curso bigint not null,
    id_usuario bigint not null,
    fecha_creacion timestamp not null,
    status boolean not null,

    constraint fk_topicos_id_curso foreign key (id_curso) references cursos(id),
    constraint fk_topicos_id_usuario foreign key (id_usuario) references usuarios(id)
);