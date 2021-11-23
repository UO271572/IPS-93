--select c.dnicorredor, c.nombre 
--from corredores c, inscripciones i 
--where i.idcarrera = 1 and i.estadoinscripcion = 'INSCRITO' and c.dnicorredor = i.dnicorredor
--order by i.fechainscripcion;

--alter table carreras add estadocarrera varchar 'ABIERTA';
--select plazasreservadas from carreras where idcarrera = 1;
--select plazasdisponibles from carreras where idcarrera = 1;


--select distinct c.nombre,cat.nombre,i.dorsal,i.tiempoinicio,i.tiempofin 
--from corredores as c 
--join inscripciones as i  on(c.dnicorredor = i.dnicorredor)
--join categorias as cat on (cat.idcarrera = i.idcarrera)
--where (cat.sexo <> 'cualquiera' and i.idcarrera = 239 and i.estadoinscripcion = 'INSCRITO' 
--and ((YEAR(CURRENT_TIMESTAMP)-YEAR(c.fechanacimiento)) between (cat.edadinicio = 18) and (cat.edadfin=34)));

--select distinct c.nombre,cat.nombre,i.dorsal,i.tiempoinicio,i.tiempofin 
--from corredores as c 
--join inscripciones as i  on(c.dnicorredor = i.dnicorredor)
--join categorias as cat on (cat.idcarrera = i.idcarrera)
--where (cat.sexo = 'Masculino' and i.idcarrera = 239 and i.estadoinscripcion = 'INSCRITO');


--LA CARRERA YA ESTA CREADA CON EL ID 239
--insert into corredores(dnicorredor,nombre,apellidos,fechanacimiento,sexo,email)values('111114A','Juan4','Juan4','2000-1-05','Hombre','juan4@juan.com');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111114A',239,'INSCRITO','2021-10-13');


--insert into carreras(idcarrera,nombre,fechacompeticion,tipo,distancia,plazasdisponibles,lugar,plazasreservadas,estadocarrera)
 --       values(239,'carreraCerrada1','2021-02-23','ciudad',1,100,'oviedo',10,'CERRADO');





--CREATE TABLE CATEGORIAS (nombre varchar(40) NOT NULL,
--edadinicio integer NOT NULL, 
--edadfin integer NOT NULL,
--SEXO varchar(15) NOT NULL,
--PRIMARY KEY (idcarrera,edadinicio,edadfin,sexo),
--idcarrera integer foreign key REFERENCES CARRERAS(IDCARRERA);

--drop table resultados;

--alter table carreras add  estadocarrera varchar null;




--borrar
--delete from inscripciones where dnicorredor = '111111A';
--delete from inscripciones where dnicorredor = '111112A';
--delete from inscripciones where dnicorredor = '111113A';
--delete from inscripciones where dnicorredor = '111114A';

--delete from corredores where dnicorredor = '111111A';
--delete from corredores where dnicorredor = '111112A';
--delete from corredores where dnicorredor = '111113A';
--delete from corredores where dnicorredor = '111114A';

--delete from carreras where idcarrera = 250;


--insertar carrera de prueba
--insert into carreras(idcarrera,nombre,fechacompeticion,tipo,distancia,plazasdisponibles,lugar,plazasreservadas,estadocarrera)
--        values(253,'carreraCerrada3','2021-02-25','ciudad',1,100,'gijon',10,'CERRADO');

--insertar corredores de prueba
--insert into corredores(dnicorredor,nombre,apellidos,fechanacimiento,sexo,email)values('111111A','Juan1','Juan1','2000-1-02','Hombre','juan1@juan.com');
--insert into corredores(dnicorredor,nombre,apellidos,fechanacimiento,sexo,email)values('111112A','Juan2','Juan2','2000-1-03','Hombre','juan2@juan.com');
--insert into corredores(dnicorredor,nombre,apellidos,fechanacimiento,sexo,email)values('111113A','Juan3','Juan3','2000-1-04','Hombre','juan3@juan.com');
--insert into corredores(dnicorredor,nombre,apellidos,fechanacimiento,sexo,email)values('111114A','Juan4','Juan4','2000-1-05','Hombre','juan4@juan.com');
--insertar inscripciones de prueba
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111111A',251,'INSCRITO','2021-10-10');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111112A',251,'INSCRITO','2021-10-11');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111113A',251,'INSCRITO','2021-10-12');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111114A',251,'INSCRITO','2021-10-13');

--insertar a carrera cerrada
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111111A',250,'INSCRITO','2021-10-10');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111112A',250,'INSCRITO','2021-10-11');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111113A',250,'INSCRITO','2021-10-12');
--insert into inscripciones(dnicorredor,idcarrera,estadoinscripcion,fechainscripcion)values('111114A',250,'INSCRITO','2021-10-13');








--HU ARREGLAR CLASIFICACIONES
--alter table carreras drop column estadocarrera;

--select max(fechafin) from plazos where idcarrera = 236 ;



















