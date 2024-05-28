-- Angel Caballero Espinosa
-- Elimino la base de datos por si existe crearla de nuevo o si hay algún fallo borrarla
drop database if exists practica_intermodular;
-- Creo la base de datos
create database practica_intermodular;
-- Uso la base de datos
use practica_intermodular;

-- Elimino la tabla por si ya existe o por si hay algún fallo recrearla de nuevo
DROP TABLE IF EXISTS Estrellas;
-- Creo la tabla Estrellas, con sus atributos
create table if not exists Estrellas(
    nombre varchar(20) primary key,
    tipo_estrella varchar(20),
    radio_km double,
    temperatura double,
    distancia_media_tierra double,
    composicion varchar(100),
    fecha_creacion date null
);

-- Elimino la tabla por si ya existe o por si hay algún fallo recrearla de nuevo
DROP TABLE IF EXISTS Planetas;
-- Creo la tabla Planetas, con sus atributos
create table if not exists Planetas(
    nombre varchar(20) primary key,
    radio_km double,
    distancia_media_sol double,
    periodo_orbital double,
    temperatura_media double,
    tipo_planeta varchar(20),
    numero_satelites int,
    fecha_creacion date null,
    estrella varchar(20),
    CONSTRAINT FK_Planetas_Estrellas FOREIGN KEY (estrella) REFERENCES Estrellas(nombre)
);

-- Elimino la tabla por si ya existe o por si hay algún fallo recrearla de nuevo
DROP TABLE IF EXISTS Satelites;
-- Creo la tabla Satelites, con sus atributos
create table if not exists Satelites(
    nombre varchar(20) primary key,
	planeta varchar(20),
    radio_km double,
    distancia_media_planeta double,
    periodo_orbital double,
    temperatura_media double,
    tipo_cuerpo varchar(20),
    fecha_creacion date null,
    CONSTRAINT FK_Satelites_Planetas FOREIGN KEY (planeta) REFERENCES Planetas(nombre)
);

    -- El campo “número de satélites” de los planetas deberá actualizarse automáticamente mediante un script PL-SQL cada vez que se inserta un satélite
DELIMITER //
create trigger act_numero_satelites -- Creamos un trigger con un nuevo nombre
after insert on Satelites -- indicamos que se ejecutara después de una inserción en la tabla Satelites.
for each row -- por cada linea le decimos que actualize los satelites en la tabla planetas incrementando en 1 
begin
    update Planetas
    set numero_satelites = numero_satelites + 1
    where nombre = new.planeta;
end //
DELIMITER ;

-- El campo “fecha de creación” deberá contar con un disparador PL-SQL que añada la fecha en la que se han añadido a la bbdd.
-- creo 3 disparadores para cada tabla para que ponga automaticamente la fecha de cuando se añadio a la base de datos
-- Para la tabla Estrellas
DELIMITER //
create trigger nueva_fecha_estrellas -- creo un trigger con su nombre
before insert on Estrellas  -- insertamos en la tabla Estrellas
for each row -- en cada linea le indicamos  que se ponga la fecha nueva en el momento que se añadio a la base
begin
    set new.fecha_creacion = NOW();
end //
DELIMITER ;

-- Para la tabla Planetas
DELIMITER //
create trigger nueva_fecha_planetas 
before insert on Planetas
for each row
begin
    set new.fecha_creacion = NOW();
end //
DELIMITER ;

-- Para la tabla satelites
DELIMITER //
create trigger nueva_fecha_satelites
before insert on Satelites
for each row
begin
    set new.fecha_creacion = NOW();
end //
DELIMITER ;


-- Aquí inserto los datos a la tabla Estrellas.
INSERT INTO Estrellas (nombre, tipo_estrella, radio_km, temperatura, distancia_media_tierra, composicion)
VALUES ('Sol', 'G2V', 696340.0, 5500.0, 149.6, '74% Hidrógeno, 24% Helio, 2% Otros');

select * from Estrellas;

-- Angel Caballero Espinosa
-- Aquí hago lo mismo pero con todos los datos de los planetas
INSERT INTO Planetas(nombre, estrella, radio_km, distancia_media_sol,periodo_orbital, temperatura_media, tipo_planeta, numero_satelites) VALUES
    ('Mercurio', 'Sol', 2439.7, 57.9, 88.0, 167.0, 'Rocoso', 0),
    ('Venus', 'Sol', 6051.8, 108.2, 225.0, 464.0, 'Rocoso', 0),
    ('Tierra', 'Sol', 6371.0, 149.6, 365.25, 15.0, 'Rocoso', 1),
    ('Marte', 'Sol', 3389.5, 227.9, 687.0, -65.0, 'Rocoso', 2),
    ('Júpiter', 'Sol', 69911.0, 778.5, 4333.0, -110.0, 'Gaseoso', 4),
    ('Saturno', 'Sol', 58232.0, 1429.4, 10759.0, -140.0, 'Gaseoso', 2),
    ('Urano', 'Sol', 25362.0, 2870.9, 30687.0, -195.0, 'Gaseoso', 2),
    ('Neptuno', 'Sol', 24622.0, 4498.3, 60190.0, -200.0, 'Gaseoso', 0);
    
select * from Planetas;

-- Angel Caballero Espinosa
-- Aquí sucede lo mismo pero para la tabla Satelites
INSERT INTO Satelites (nombre, planeta, radio_km, distancia_media_planeta, periodo_orbital, temperatura_media, tipo_cuerpo) VALUES
    ('Luna', 'Tierra', 1737.4, 384400.0, 27.3, -53.0, 'Sólido/Rocoso'),
    ('Fobos', 'Marte', 11.1, 9378.0, 0.3, -40.0, 'Sólido/Rocoso'),
    ('Deimos', 'Marte', 6.2, 23460.0, 1.3, -40.0, 'Sólido/Rocoso'),
    ('Ío', 'Júpiter', 1821.6, 421700.0, 1.8, -143.0, 'Sólido/Rocoso'),
    ('Europa', 'Júpiter', 1560.8, 670900.0, 3.5, -160.0, 'Sólido/Hielo'),
    ('Ganimedes', 'Júpiter', 2634.1, 1070400.0, 7.2, -163.0, 'Sólido/Hielo'),
    ('Calisto', 'Júpiter', 2410.3, 1882700.0, 16.7, -139.0, 'Sólido/Hielo'),
    ('Titán', 'Saturno', 2575.5, 1222000.0, 15.9, -179.0, 'Sólido/Hielo'),
    ('Encélado', 'Saturno', 252.1, 238000.0, 1.4, -201.0, 'Sólido/Hielo'),
    ('Titania', 'Urano', 788.4, 435900.0, 8.7, -203.0, 'Sólido/Hielo'),
    ('Oberón', 'Urano', 761.4, 583500.0, 13.5, -203.0, 'Sólido/Hielo');
    
 select * from Satelites;



