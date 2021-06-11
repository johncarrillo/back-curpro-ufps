--roles por defecto
insert into rol (rol_nombre) values ('Administrador');
insert into rol (rol_nombre) values ('Secretario Junta FRIE');
insert into rol (rol_nombre) values ('Director de Programa');
insert into rol (rol_nombre) values ('Docente');
insert into rol (rol_nombre) values ('Estudiante');

-- usuarios
insert into usuario (usua_correo, usua_nombre, usua_apellido) values ('johnjairoci@ufps.edu.co', 'John Jairo', 'Carrillo Ibarra');
insert into usuario (usua_correo, usua_nombre, usua_apellido) values ('joseandreshf@ufps.edu.co', 'jose Andres', 'Hernandez Florez');
insert into usuario (usua_correo, usua_nombre, usua_apellido) values ('jjcarrillo9293@gmail.com', 'John', 'Docente');
insert into usuario (usua_correo, usua_nombre, usua_apellido) values ('john.carrillo@sofka.com.co', 'John Jairo', 'Estudiante');

-- rol x usuarios
insert into rol_usuario (rol_id, usua_id) values (3, 1);
insert into rol_usuario (rol_id, usua_id) values (3, 2);
insert into rol_usuario (rol_id, usua_id) values (4, 3);
insert into rol_usuario (rol_id, usua_id) values (5, 4);
insert into rol_usuario (rol_id, usua_id) values (5, 1);


--info estudiante
insert into estudiante_info (rous_id, esin_codigo, esin_cedula, esin_direccion, esin_telefono_personal, esin_telefono_familiar) values (4, '1151168', '1090479106', 'calle 1n #9a - 08 brr. Sevilla', '3004230343', '5789816');
insert into estudiante_info (rous_id, esin_codigo, esin_cedula, esin_direccion, esin_telefono_personal, esin_telefono_familiar) values (5, '1151150', '2988226', 'calle 1n #9a - 08 brr. Sevilla', '32725565', '5789816');

-- dependencia
insert into dependencia (depe_nombre, rous_id) values ('Ingenieria de Sistemas', 1);

--tipos de curso
insert into tipo_curso (ticu_nombre) values ('Cursos de profundización');
insert into tipo_curso (ticu_nombre) values ('Congresos');
insert into tipo_curso (ticu_nombre) values ('Diplomados');
insert into tipo_curso (ticu_nombre) values ('Seminarios');
insert into tipo_curso (ticu_nombre) values ('Simposios');
insert into tipo_curso (ticu_nombre) values ('Talleres');
insert into tipo_curso (ticu_nombre) values ('Encuentros');
insert into tipo_curso (ticu_nombre) values ('Especializaciones');
insert into tipo_curso (ticu_nombre) values ('maestrías');
insert into tipo_curso (ticu_nombre) values ('cursos de extensión');
insert into tipo_curso (ticu_nombre) values ('Otros');

--tipos de actividad
insert into tipo_actividad (tiac_nombre, tiac_descripcion) values ('Evaluación', 'Evaluación de tema especifico');
insert into tipo_actividad (tiac_nombre, tiac_descripcion) values ('Taller', 'Trabajo en clase');
insert into tipo_actividad (tiac_nombre, tiac_descripcion) values ('Trabajo en clase', 'Trabajo en casa, con fecha limite de entrega');

--Estado de cursos
insert into estado_curso (escu_nombre) values ('Enviado - Sin revisión');
insert into estado_curso (escu_nombre) values ('Enviado - Con Revisión');
insert into estado_curso (escu_nombre) values ('Rechazado');
insert into estado_curso (escu_nombre) values ('Rechazado - Con Observaciones');
insert into estado_curso (escu_nombre) values ('Aceptado');
insert into estado_curso (escu_nombre) values ('Matricula');
insert into estado_curso (escu_nombre) values ('Inscripcion');
insert into estado_curso (escu_nombre) values ('En Curso');
insert into estado_curso (escu_nombre) values ('Evaluación');
insert into estado_curso (escu_nombre) values ('Terminado');

--Estado de LA MATRICULA
insert into estado_matricula (esma_nombre) values ('Pendiente');
insert into estado_matricula (esma_nombre) values ('Pago');
insert into estado_matricula (esma_nombre) values ('Suspendida');
insert into estado_matricula (esma_nombre) values ('Retirado');

--Estado de banco
insert into banco (banc_nombre) values ('Bancolombia');
insert into banco (banc_nombre) values ('Banco BBVA Colombia');

--item de ingreso
insert into item_ingreso (itin_consecutivo, itin_nombre, itin_valor) values (1, 'INGRESOS (Ver Anexo 1)', 80000);
insert into item_ingreso (itin_consecutivo, itin_nombre, itin_valor, itin_padre) values (1, 'Inscripciones', 20000, 1);
insert into item_ingreso (itin_consecutivo, itin_nombre, itin_valor, itin_padre) values (2, 'Matriculas', 60000, 1);

--item de gasto
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor) values (2, 'Gasto', 20000);

insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (1, 'Servicios Profesionales', 20000, 1);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (1, 'Servicio Educativo Horonarios (Ver Anexo 2)', 10000, 2);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (2, 'Coordinación (Ver Anexo 3)', 10000, 2);

insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (2, 'Gastos Generales', 10000, 1);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (1, 'Apoyo Logistico', 10000, 5);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (2, 'Material y Suministro (Material Didactico y de Laboratorio)', 10000, 5);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (1, 'Material Didactico y de Laboratorio', 10000, 7);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (2, 'Material de consumo (Papeleria, tintas, acetatos, escarapelas)', 10000, 7);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (3, 'Impresos y Publicaciones', 10000, 5);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (1, 'Certificados (Diplomas)', 10000, 10);

insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (3, 'Otros Gastos', 10000, 1);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (1, 'Alquiler de Aula', 10000, 12);
insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (2, 'Otros (En caso de incurrir en otro gasto no descrito mencionelo en este item y justifiquelo)', 10000, 12);

insert into item_gasto (itga_consecutivo, itga_nombre, itga_valor, itga_padre) values (4, 'Gastos de Administración FRIE (10% ingresos según resolución N° X)', 20000, 1);


--inscripcion de matricula
insert into inscripcion_matricula (itin_id, inma_tipo_participante, inma_cantidad, inma_valor_unitario, inma_valor_total) values (1, 'Estudiante', 5, 1000, 5000);
insert into inscripcion_matricula (itin_id, inma_tipo_participante, inma_cantidad, inma_valor_unitario, inma_valor_total) values (1, 'Practicante', 2, 500, 1000);

--servicio educativo
insert into servicio_educativo (itga_id, seed_nombre_docente, seed_escolaridad, seed_escalafon, seed_puntaje, seed_valor_punto, seed_cantidad_hora, seed_valor_hora, seed_valor_total) values (3, 'John Jairo Carrillo', 'Postgrado', 'N/A', 10, 20, 10, 50, 500);
insert into servicio_educativo (itga_id, seed_nombre_docente, seed_escolaridad, seed_escalafon, seed_puntaje, seed_valor_punto, seed_cantidad_hora, seed_valor_hora, seed_valor_total) values (3, 'Juan Pablo Montolla', 'Postgrado', 'N/A', 10, 20, 10, 20, 200);

--coordinacion de oferta academica
insert into coordinacion_oferta_academica (itga_id, cofa_nombre, cofa_perfil, cofa_idoneidad_competencia, cofa_valor_total) values (4, 'Pilar Rodriguez', 'Docente de ingenieria del software', 'SI', 500);

--apoyo logistico
insert into apoyo_logistico (itga_id, aplo_numero, aplo_actividad, aplo_valor_total) values (6, 1, 'Conferencia', 100);

--Materiales y suministro
insert into material_suministro (itga_id, masu_numero, masu_bien_servicio, masu_cantidad, masu_valor_unitario, masu_valor_total) values (7, 1, 'Internet satelital', 1, 50, 50);
insert into material_suministro (itga_id, masu_numero, masu_bien_servicio, masu_cantidad, masu_valor_unitario, masu_valor_total) values (7, 2, 'Salida de campo', 2, 220, 440);

--impresos publicaciones
insert into impreso_publicacion (itga_id, impu_numero, impu_publicacion, impu_cantidad, impu_valor_unitario, impu_valor_total) values (10, 1, 'Revista ACM', 2, 100, 200);

--alquiler de aula
insert into alquiler_aula (itga_id, alau_numero, alau_dependencia_presta_servicio, alau_cantidad, alau_valor_unitario, alau_valor_total) values (13, 1, 'Ingenieria de Sistemas', 2, 20, 40);

--otros
insert into otro_anexo (itga_id, otan_numero, otan_descripcion, otan_valor_total) values (14, 2, 'papeleria', 10);
insert into otro_anexo (itga_id, otan_numero, otan_descripcion, otan_valor_total) values (14, 1, 'VideoBeam', 20);

--presupuesto
insert into presupuesto (pres_valor_ingreso, pres_valor_gasto, pres_valor_utilidad, pres_fondo_investigacion_universitario, pres_utilidad_neta, itga_id, itin_id) values (80000, 60000, 20000, 2000, 18000, 1, 1);

--curso
insert into curso (curs_cantidad_horas, curs_descripcion, curs_fecha_inicio, curs_fecha_fin, curs_fecha_limite_pago, curs_fecha_limite_retiro, curs_justificacion_rechazo, curs_nombre, curs_ticu_otro, escu_id, ticu_id, pres_id, depe_id) values (4, 'Curso de Prueba', '2021/01/01', '2021/02/01', '2021/01/01', '2021/01/02', 'Porque no sumo bien', 'Desarrollo de Software', null, 1, 1, 1, 1);

--modulo curso
insert into modulo_curso (mocu_horas_clase, mocu_horas_independientes, mocu_nombre, curs_id, docente_id) values (4, 16, 'Programación Reactiva', 1, 3);
insert into modulo_curso (mocu_horas_clase, mocu_horas_independientes, mocu_nombre, curs_id) values (4, 16, 'Programación Reactiva', 1);

--MATRICULA
insert into matricula (banc_id, curs_id, esma_id, esin_id ) values (1, 1, 2, 1);
insert into matricula (banc_id, curs_id, esma_id, esin_id ) values (1, 1, 2, 2);

--actividad
insert into actividad (acti_nombre, acti_descripcion, mocu_id, tiac_id ) values ('primera actividad', 'esta es la primera actividad', 1, 1);
insert into actividad (acti_nombre, acti_descripcion, mocu_id, tiac_id ) values ('segunda actividad', 'esta es la segunda actividad', 1, 1);


--actividad_estudiante
insert into actividad_estudiante (aces_calificacion, acti_id, esin_id) values (3.2, 1, 1);
insert into actividad_estudiante (aces_calificacion, acti_id, esin_id) values (3.2, 1, 2);

insert into actividad_estudiante (aces_calificacion, acti_id, esin_id) values (2.1, 2, 1);

