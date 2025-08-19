create table usuarios (
    id bigint generated always as identity primary key,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    contrasena varchar(300),
);