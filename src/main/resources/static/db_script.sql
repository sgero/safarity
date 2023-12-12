drop table if exists asistente;
drop table if exists ticket;
drop table if exists evento_participante;
drop table if exists evento;
drop table if exists organizacion;
drop table if exists participante;
drop table if exists token;
drop table if exists usuario;





create table usuario(

                        id serial not null,
                        alias varchar(100) not null,
                        password varchar(100) not null,
                        activo bool default true,
                        rol int2 not null,
                        primary key (id)

);

create table participante(

                             id serial not null,
                             nombre varchar(100) not null,
                             apellidos varchar(100) not null,
                             direccion varchar(200) not null,
                             email varchar(100) not null,
                             dni varchar(9) not null,
                             telefono varchar(50) not null,
                             fecha_nacimiento date not null,
                             saldo numeric(10,2) default 0.0,
                             id_usuario int4 not null,
                             activo bool default true,
                             primary key (id),
                             constraint participante_usuario_fk foreign key (id_usuario) references usuario (id)
);


create table organizacion(

                             id serial not null,
                             nombre varchar(100) not null,
                             email varchar(100) not null,
                             cif varchar(9) not null,
                             telefono varchar(50) not null,
                             fecha_fundacion date not null,
                             direccion varchar(200),
                             info varchar(1000) not null,
                             sitio_web varchar(1000) not null,
                             logo varchar(1000) not null,
                             id_usuario int4 not null,
                             activo bool default true,
                             primary key (id),
                             constraint organizacion_usuario_fk foreign key (id_usuario) references usuario (id)
);


create table evento(

                       id serial not null,
                       nombre varchar(100) not null,

--                     CUANDO COMIENZA EL EVENTO
                       fecha_inicio timestamp(6) not null,

--                     CUANDO ACABA EL EVENTO
                       fecha_fin timestamp(6) not null,

                       tipo_pago int2 not null,
                       tipo_evento int2 not null,
                       aforo integer not null,
                       direccion varchar(200) not null,

--                     CUANDO ESTA DISPONIBLE LA COMPRA DE TICKET
                       fecha_venta timestamp(6) not null,

--                     CUANDO SE MUESTRA EL EVENTO
                       fecha_lanzamiento timestamp(6) not null,

                       descripcion varchar(1000) not null,
                       imagen varchar(1000) not null,
                       precio numeric(5,2),
                       id_organizacion int4 not null,
                       activo bool default true,
                       primary key (id),
                       constraint evento_organizacion_fk foreign key (id_organizacion) references organizacion (id)
);

create table ticket(

                       id serial not null,
                       dinero_aportado numeric(5,2) not null,
                       fecha_compra date not null,
                       id_participante int4 not null,
                       id_evento int4 not null,
                       activo bool default true,
                       primary key (id),
                       constraint ticket_participante_fk foreign key (id_participante) references participante (id),
                       constraint ticket_evento_fk foreign key (id_evento) references evento (id)
);

create table asistente(

                          id serial not null,
                          nombre varchar(100) not null,
                          apellidos varchar(100) not null,
                          direccion varchar(200) not null,
                          email varchar(100) not null,
                          dni varchar(9) not null,
                          telefono varchar(50) not null,
                          fecha_nacimiento date not null,
                          id_ticket int4 not null,
                          activo bool default true,
                          primary key (id),
                          constraint asistente_ticket_fk foreign key (id_ticket) references ticket (id)
);

create table evento_participante(

    id serial not null,
    id_evento int4 not null,
    id_participante int4 not null,
    primary key (id),
    constraint evento_evento_participante foreign key (id_evento) references evento(id),
    constraint participante_evento_participante foreign key (id_participante) references participante(id)

);


create table token(

                      id serial not null,
                      token varchar(800) not null,
                      fecha_expiracion timestamp(6) not null,
                      id_usuario int4 not null,
                      constraint token_usuario foreign key (id_usuario) references usuario(id)

);


ALTER TABLE asistente
    ALTER COLUMN id_ticket DROP NOT NULL;




--MODIFICACIONES PARA AÑADIR EL CAMPO DE RESEÑA A LA BBDD FINAL
-- Asegúrate de que no haya restricciones de clave foránea activas
ALTER TABLE evento_participante
    DROP CONSTRAINT IF EXISTS evento_evento_participante,
    DROP CONSTRAINT IF EXISTS participante_evento_participante;

-- Agregar el campo de resenya
ALTER TABLE evento_participante
    ADD COLUMN resenya VARCHAR(1000);


-- Vuelve a agregar las restricciones de clave foránea
ALTER TABLE evento_participante
    ADD CONSTRAINT evento_evento_participante FOREIGN KEY (id_evento) REFERENCES evento(id),
    ADD CONSTRAINT participante_evento_participante FOREIGN KEY (id_participante) REFERENCES participante(id);



-- HASTA AQUÍ EL ESQUEMA FINAL

-- CONSULTAS


select * from usuario;
select * from participante;
select * from asistente;
select * from organizacion;
select * from evento;
select * from ticket;

select e.id_evento from evento_participante e where e.id_participante = 2;
