create table cursos (
    id bigint generated always as identity primary key,
    nombreCurso varchar(100) not null,
    categoria varchar(100) not null,
);