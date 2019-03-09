delete from ClienteEntity;
delete from EquipoEntity;
delete from PartidoEntity;
delete from PostEntity;
delete from BlogEntity;
delete from CampeonatoEntity;
delete from AgendaEntity;
delete from AmistosoEntity;
delete from CanchaEntity;
delete from EntrenamientoEntity;
delete from PropietarioEntity;
delete from ReservaEntity;
delete from FranjaEntity;

select * from CampeonatoEntity;

insert into ClienteEntity (id,nombre) values (0, 'Santiago Serrano');
insert into ClienteEntity (id,nombre) values (1, 'Juan Camilo Garcia');
insert into ClienteEntity (id,nombre) values (2, 'Santiago Barbosa');
insert into ClienteEntity (id,nombre) values (3, 'Nicolas de la Hoz');
insert into ClienteEntity (id,nombre) values (4, 'Nicolas Poch');


insert intp BlogEntity (id, descripcion, identificador, nombre, campeonato_id) values (0, 'Las location services', 0, 'Desarrollo', 0);
insert intp BlogEntity (id, descripcion, identificador, nombre, campeonato_id) values (1, 'Te la creiste wey', 1, 'JAJAJA', 1);

