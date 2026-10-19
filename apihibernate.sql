create database apihibernate;

use apihibernate;

select * from pelicula;

select
        p1_0.id,
        p1_0.director,
        p1_0.disponible,
        p1_0.duracion,
        p1_0.genero,
        p1_0.nombre
    from
        pelicula p1_0;
    where
        p1_0.genero like '%on%';
        
        select * from pelicula;