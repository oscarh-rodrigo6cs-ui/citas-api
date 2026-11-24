-- Roles
INSERT INTO roles (id, nombre) VALUES
(1, 'GERENTE'),
(2, 'TERAPISTA'),
(3, 'ADMIN'),
(4, 'CLIENTE'),
(5, 'RECEPCIONISTA');

-- Usuarios (contraseña Clave123)
INSERT INTO usuarios (id, email, password, rol_id, activo, creado_en) VALUES
(1, 'gerente@terapias.pe', 'Clave123', 1, TRUE, CURRENT_TIMESTAMP),
(2, 'gerente2@terapias.pe', 'Clave123', 1, TRUE, CURRENT_TIMESTAMP),
(3, 'admin@terapias.pe', 'Clave123', 3, TRUE, CURRENT_TIMESTAMP),
(4, 'maria.gutierrez@terapias.pe', 'Clave123', 2, TRUE, CURRENT_TIMESTAMP),
(5, 'carlos.paredes@terapias.pe', 'Clave123', 2, TRUE, CURRENT_TIMESTAMP),
(6, 'lucia.herrera@terapias.pe', 'Clave123', 2, TRUE, CURRENT_TIMESTAMP),
(7, 'pedro.sanchez@terapias.pe', 'Clave123', 2, TRUE, CURRENT_TIMESTAMP),
(8, 'sofia.vargas@terapias.pe', 'Clave123', 2, TRUE, CURRENT_TIMESTAMP),
(9, 'diego.ramirez@terapias.pe', 'Clave123', 2, FALSE, CURRENT_TIMESTAMP),
(10, 'rosa.medina@terapias.pe', 'Clave123', 5, TRUE, CURRENT_TIMESTAMP),
(11, 'jorge.castro@terapias.pe', 'Clave123', 5, TRUE, CURRENT_TIMESTAMP),
(12, 'elena.ruiz@terapias.pe', 'Clave123', 5, TRUE, CURRENT_TIMESTAMP),
(13, 'ana.quispe@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(14, 'luis.ramos@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(15, 'carmen.flores@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(16, 'jorge.mendoza@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(17, 'rosa.castillo@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(18, 'miguel.chavez@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(19, 'patricia.salazar@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(20, 'fernando.huaman@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(21, 'gabriela.lozano@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP),
(22, 'ricardo.vilchez@correo.com', 'Clave123', 4, FALSE, CURRENT_TIMESTAMP),
(23, 'nuevo.terapista@terapias.pe', 'Clave123', 2, TRUE, CURRENT_TIMESTAMP),
(24, 'nueva.recepcionista@terapias.pe', 'Clave123', 5, TRUE, CURRENT_TIMESTAMP),
(25, 'nuevo.cliente@correo.com', 'Clave123', 4, TRUE, CURRENT_TIMESTAMP);

-- Parametros
INSERT INTO parametros (id, clave, valor, descripcion) VALUES
(1, 'NOMBRE_EMPRESA', 'Centro de Terapias Bienestar', 'Nombre que aparece en los comprobantes'),
(2, 'RUC', '20999999991', 'RUC de prueba para las facturas'),
(3, 'IGV', '0.18', 'Tasa del IGV, el costo de los servicios ya lo incluye'),
(4, 'HORA_APERTURA', '08:00', 'Hora en que abre el centro'),
(5, 'HORA_CIERRE', '20:00', 'Hora en que cierra el centro'),
(6, 'DURACION_SESION_DEFECTO', '50', 'Minutos, si el servicio no indica duración'),
(7, 'SERIE_BOLETA', 'B001', 'Serie de las boletas'),
(8, 'SERIE_FACTURA', 'F001', 'Serie de las facturas');

-- Servicios
INSERT INTO servicios (id, titulo, descripcion, costo, duracion, foto, activo) VALUES
(1, 'Fisioterapia', 'Rehabilitación física y manejo del dolor', 110.00, 50, '/img/servicios/fisioterapia.jpg', TRUE),
(2, 'Psicoterapia', 'Sesión individual de terapia psicológica', 130.00, 60, '/img/servicios/psicoterapia.jpg', TRUE),
(3, 'Masoterapia', 'Masaje terapéutico de relajación y descontracturante', 95.00, 45, '/img/servicios/masoterapia.jpg', TRUE),
(4, 'Terapia de lenguaje', 'Evaluación y terapia del habla y la comunicación', 100.00, 45, '/img/servicios/lenguaje.jpg', TRUE),
(5, 'Terapia ocupacional', 'Recuperación de habilidades para la vida diaria', 105.00, 50, '/img/servicios/ocupacional.jpg', TRUE),
(6, 'Drenaje linfático', 'Masaje para mejorar la circulación linfática', 120.00, 60, '/img/servicios/drenaje.jpg', TRUE),
(7, 'Electroterapia', 'Aplicación de corrientes para aliviar el dolor', 80.00, 30, '/img/servicios/electroterapia.jpg', TRUE),
(8, 'Acupuntura', 'Medicina tradicional con agujas finas', 90.00, 40, NULL, FALSE);

-- Recepcionistas
INSERT INTO recepcionistas (id, usuario_id, nombres, apellidos, dni, telefono, turno) VALUES
(1, 10, 'Rosa', 'Medina Torres', '47813459', '987200301', 'MANANA'),
(2, 11, 'Jorge', 'Castro Lima', '48924560', '987200302', 'TARDE'),
(3, 12, 'Elena', 'Ruiz Paz', '49035671', '987200303', 'MANANA');

-- Terapistas
INSERT INTO terapistas (id, usuario_id, nombres, apellidos, dni, telefono, activo) VALUES
(1, 4, 'María', 'Gutiérrez León', '41257893', '987100201', TRUE),
(2, 5, 'Carlos', 'Paredes Ríos', '42368904', '987100202', TRUE),
(3, 6, 'Lucía', 'Herrera Campos', '43479015', '987100203', TRUE),
(4, 7, 'Pedro', 'Sánchez Cruz', '44580126', '987100204', TRUE),
(5, 8, 'Sofía', 'Vargas Núñez', '45691237', '987100205', TRUE),
(6, 9, 'Diego', 'Ramírez Soto', '46702348', '987100206', FALSE);

-- Horarios
INSERT INTO horarios (id, terapista_id, dia_semana, hora_inicio, hora_fin) VALUES
(1, 1, 1, '08:00:00', '14:00:00'),
(2, 1, 2, '08:00:00', '14:00:00'),
(3, 1, 3, '08:00:00', '14:00:00'),
(4, 1, 4, '08:00:00', '14:00:00'),
(5, 1, 5, '08:00:00', '14:00:00'),
(6, 1, 6, '08:00:00', '14:00:00'),
(7, 2, 1, '14:00:00', '20:00:00'),
(8, 2, 2, '14:00:00', '20:00:00'),
(9, 2, 3, '14:00:00', '20:00:00'),
(10, 2, 4, '14:00:00', '20:00:00'),
(11, 2, 5, '14:00:00', '20:00:00'),
(12, 2, 6, '14:00:00', '20:00:00'),
(13, 3, 1, '08:00:00', '14:00:00'),
(14, 3, 2, '08:00:00', '14:00:00'),
(15, 3, 3, '08:00:00', '14:00:00'),
(16, 3, 4, '08:00:00', '14:00:00'),
(17, 3, 5, '08:00:00', '14:00:00'),
(18, 3, 6, '08:00:00', '14:00:00'),
(19, 4, 1, '10:00:00', '16:00:00'),
(20, 4, 2, '10:00:00', '16:00:00'),
(21, 4, 3, '10:00:00', '16:00:00'),
(22, 4, 4, '10:00:00', '16:00:00'),
(23, 4, 5, '10:00:00', '16:00:00'),
(24, 4, 6, '10:00:00', '16:00:00'),
(25, 5, 1, '14:00:00', '20:00:00'),
(26, 5, 2, '14:00:00', '20:00:00'),
(27, 5, 3, '14:00:00', '20:00:00'),
(28, 5, 4, '14:00:00', '20:00:00'),
(29, 5, 5, '14:00:00', '20:00:00'),
(30, 5, 6, '14:00:00', '20:00:00'),
(31, 6, 6, '08:00:00', '12:00:00');

-- Terapista Servicios
INSERT INTO terapista_servicios (id, terapista_id, servicio_id) VALUES
(1, 1, 1),
(2, 1, 5),
(3, 1, 7),
(4, 2, 2),
(5, 3, 3),
(6, 3, 6),
(7, 4, 1),
(8, 4, 4),
(9, 4, 5),
(10, 4, 7),
(11, 5, 3),
(12, 5, 6),
(13, 5, 8),
(14, 6, 1);

-- Clientes
INSERT INTO clientes (id, usuario_id, nombres, apellidos, dni, fecha_nacimiento, telefono, correo) VALUES
(1, 13, 'Ana María', 'Quispe Huamán', '70123451', '1990-03-15', '951000001', 'ana.quispe@correo.com'),
(2, 14, 'Luis Alberto', 'Ramos Torres', '70234562', '1985-07-22', '951000002', 'luis.ramos@correo.com'),
(3, 15, 'Carmen Rosa', 'Flores Díaz', '70345673', '2001-11-05', NULL, 'carmen.flores@correo.com'),
(4, 16, 'Jorge Luis', 'Mendoza Salas', '70456784', '1978-01-30', '951000004', 'jorge.mendoza@correo.com'),
(5, 17, 'Rosa Elena', 'Castillo Vega', '70567895', '1995-09-12', '951000005', 'rosa.castillo@correo.com'),
(6, 18, 'Miguel Ángel', 'Chávez Rojas', '70678906', '1968-04-18', '951000006', 'miguel.chavez@correo.com'),
(7, 19, 'Patricia', 'Salazar Gómez', '70789017', '1999-12-01', '951000007', 'patricia.salazar@correo.com'),
(8, 20, 'Fernando', 'Huamán Ccori', '70890128', '1982-06-25', '951000008', 'fernando.huaman@correo.com'),
(9, 21, 'Gabriela', 'Lozano Pérez', '70901239', '2004-02-14', '951000009', 'gabriela.lozano@correo.com'),
(10, 22, 'Ricardo', 'Vílchez Arias', '71012340', '1975-10-09', '951000010', 'ricardo.vilchez@correo.com'),
(11, NULL, 'Teresa', 'Ponce Aguilar', '71123451', '1958-08-03', '951000011', NULL),
(12, NULL, 'Óscar', 'Navarro Silva', '71234562', '2006-05-20', '951000012', NULL);

-- Antecedentes
INSERT INTO antecedentes (id, cliente_id, fecha, descripcion) VALUES
(1, 1, '2025-06-10', 'Contractura lumbar recurrente desde hace 2 años: empeora al cargar peso.'),
(2, 1, '2025-09-02', 'Alergia a la penicilina (reacción en la piel). No tomar antibióticos sin consultar.'),
(3, 2, '2025-07-15', 'Ansiedad generalizada en tratamiento con psicólogo. Duerme mal en épocas de estrés.'),
(4, 3, '2025-05-20', 'Operación de rodilla derecha (menisco) en 2025. Rehabilitación incompleta.'),
(5, 4, '2025-04-08', 'Hipertensión arterial controlada. Toma losartán 50 mg cada mañana.'),
(6, 4, '2025-10-01', 'Mareos al levantarse rápido. Medir la presión antes de cada sesión.'),
(7, 5, '2025-08-12', 'Tendinitis en hombro izquierdo por trabajo en computadora.'),
(8, 6, '2025-03-25', 'Artrosis de cadera. Prótesis de cadera derecha en 2024: evitar giros bruscos.'),
(9, 6, '2025-11-05', 'Dolor de rodilla izquierda al subir escaleras.'),
(10, 7, '2025-06-30', 'Alergia al látex: usar guantes de nitrilo.'),
(11, 8, '2025-09-18', 'Esguince de tobillo grado II (deporte). Ya camina sin dolor.'),
(12, 9, '2025-02-14', 'Retraso leve del lenguaje en la infancia: hoy solo seguimiento.'),
(13, 11, '2025-01-20', 'Diabetes tipo 2 con metformina 850 mg. Revisar los pies en cada sesión.'),
(14, 11, '2025-12-03', 'Hormigueo en las manos por las noches.'),
(15, 12, '2025-07-07', 'Fractura de muñeca derecha consolidada: falta fuerza al agarrar.');

-- Consultas
INSERT INTO consultas (id, cliente_id, usuario_id, fecha, indicaciones) VALUES
(1, 1, 1, '2025-10-15', 'Dolor lumbar crónico. Evitar cargar peso y hacer los ejercicios en casa.'),
(2, 2, 2, '2025-10-20', 'Ansiedad por trabajo. Continuar con sesiones semanales de psicoterapia.'),
(3, 3, 1, '2025-10-25', 'Rehabilitación posoperatoria de rodilla. Fortalecer cuádriceps.'),
(4, 5, 1, '2025-10-30', 'Tendinitis de hombro. Reposo relativo y terapia física.'),
(5, 6, 2, '2025-11-04', 'Artrosis de cadera. Mantener actividad suave y terapia ocupacional.'),
(6, 8, 1, '2025-11-12', 'Esguince de tobillo. Terapia física y vendaje funcional.'),
(7, 1, 2, '2025-11-19', 'Control: mejora del dolor lumbar. Agregar masoterapia.'),
(8, 9, 1, '2025-11-21', 'Evaluación del habla. Iniciar terapia de lenguaje.');

-- Consulta Servicios
INSERT INTO consulta_servicios (id, consulta_id, servicio_id, sesiones) VALUES
(1, 1, 1, 10),
(2, 1, 7, 5),
(3, 2, 2, 8),
(4, 3, 1, 12),
(5, 3, 5, 4),
(6, 4, 1, 6),
(7, 4, 7, 6),
(8, 5, 5, 8),
(9, 5, 3, 4),
(10, 6, 1, 6),
(11, 7, 3, 4),
(12, 8, 4, 10);

-- Citas
INSERT INTO citas (id, cliente_id, terapista_id, servicio_id, recepcionista_id, hora_programada, hora_inicio, hora_fin, estado, valoracion) VALUES
(1, 1, 1, 1, 1, '2025-10-24 08:00:00', '2025-10-24 08:03:00', '2025-10-24 08:53:00', 'ATENDIDA', 5),
(2, 1, 1, 1, 1, '2025-10-27 09:00:00', '2025-10-27 09:05:00', '2025-10-27 09:50:00', 'ATENDIDA', 4),
(3, 1, 1, 1, 1, '2025-10-31 10:00:00', '2025-10-31 10:00:00', '2025-10-31 10:45:00', 'ATENDIDA', 5),
(4, 1, 4, 7, 1, '2025-11-03 10:00:00', '2025-11-03 10:03:00', '2025-11-03 10:28:00', 'ATENDIDA', 4),
(5, 3, 1, 1, 1, '2025-10-29 11:00:00', '2025-10-29 11:10:00', '2025-10-29 12:00:00', 'ATENDIDA', 5),
(6, 3, 1, 1, 1, '2025-11-05 12:00:00', '2025-11-05 12:00:00', '2025-11-05 12:45:00', 'ATENDIDA', 3),
(7, 3, 4, 5, 1, '2025-11-07 11:00:00', '2025-11-07 11:05:00', '2025-11-07 12:00:00', 'ATENDIDA', 4),
(8, 2, 2, 2, 2, '2025-10-26 14:00:00', '2025-10-26 14:00:00', '2025-10-26 15:00:00', 'ATENDIDA', 5),
(9, 2, 2, 2, 2, '2025-11-02 15:00:00', '2025-11-02 15:00:00', '2025-11-02 16:05:00', 'ATENDIDA', 5),
(10, 2, 2, 2, NULL, '2025-11-09 16:00:00', NULL, NULL, 'CANCELADA', NULL),
(11, 5, 4, 1, 1, '2025-11-08 12:00:00', '2025-11-08 12:00:00', '2025-11-08 12:45:00', 'ATENDIDA', 4),
(12, 5, 4, 7, 1, '2025-11-10 13:00:00', '2025-11-10 13:00:00', '2025-11-10 13:25:00', 'ATENDIDA', 5),
(13, 6, 3, 3, 1, '2025-11-11 08:00:00', '2025-11-11 08:10:00', '2025-11-11 09:00:00', 'ATENDIDA', 5),
(14, 6, 5, 6, 2, '2025-11-13 14:00:00', '2025-11-13 14:00:00', '2025-11-13 15:00:00', 'ATENDIDA', 4),
(15, 8, 1, 1, 1, '2025-11-15 13:00:00', '2025-11-15 13:00:00', '2025-11-15 13:50:00', 'ATENDIDA', 4),
(16, 4, 3, 6, NULL, '2025-11-16 09:00:00', NULL, NULL, 'CANCELADA', NULL),
(17, 7, 5, 3, 2, '2025-11-17 15:00:00', '2025-11-17 15:03:00', '2025-11-17 15:53:00', 'ATENDIDA', 3),
(18, 1, 3, 3, 1, '2025-11-19 10:00:00', '2025-11-19 10:00:00', '2025-11-19 10:40:00', 'ATENDIDA', NULL),
(19, 11, 4, 5, 2, '2025-11-20 14:00:00', '2025-11-20 14:10:00', '2025-11-20 15:00:00', 'ATENDIDA', NULL),
(20, 12, 1, 7, 1, '2025-11-21 08:30:00', '2025-11-21 08:40:00', '2025-11-21 09:10:00', 'ATENDIDA', 4),
(21, 2, 2, 2, 2, '2025-11-22 17:00:00', '2025-11-22 17:00:00', '2025-11-22 18:00:00', 'ATENDIDA', 5),
(22, 1, 1, 1, 1, '2025-11-23 09:30:00', '2025-11-23 09:33:00', '2025-11-23 10:18:00', 'ATENDIDA', 5),
(23, 3, 1, 1, 1, '2025-11-23 11:30:00', '2025-11-23 11:40:00', NULL, 'EN_ATENCION', NULL),
(24, 8, 4, 1, NULL, '2025-11-23 10:30:00', NULL, NULL, 'RESERVADA', NULL),
(25, 6, 5, 6, NULL, '2025-11-23 15:30:00', NULL, NULL, 'RESERVADA', NULL),
(26, 9, 3, 6, NULL, '2025-11-23 12:00:00', NULL, NULL, 'CANCELADA', NULL),
(27, 1, 3, 3, NULL, '2025-11-25 10:30:00', NULL, NULL, 'RESERVADA', NULL),
(28, 2, 2, 2, NULL, '2025-11-25 18:00:00', NULL, NULL, 'RESERVADA', NULL),
(29, 3, 1, 1, NULL, '2025-11-26 12:30:00', NULL, NULL, 'RESERVADA', NULL),
(30, 5, 4, 1, NULL, '2025-11-26 11:30:00', NULL, NULL, 'RESERVADA', NULL),
(31, 9, 5, 8, NULL, '2025-11-27 16:00:00', NULL, NULL, 'RESERVADA', NULL),
(32, 4, 3, 3, NULL, '2025-11-28 08:30:00', NULL, NULL, 'RESERVADA', NULL),
(33, 7, 5, 6, NULL, '2025-11-29 17:00:00', NULL, NULL, 'RESERVADA', NULL),
(34, 11, 4, 5, NULL, '2025-11-30 15:00:00', NULL, NULL, 'RESERVADA', NULL),
(35, 8, 1, 1, NULL, '2025-12-01 10:30:00', NULL, NULL, 'RESERVADA', NULL),
(36, 12, 4, 7, NULL, '2025-12-02 12:30:00', NULL, NULL, 'RESERVADA', NULL);

-- Comprobantes
INSERT INTO comprobantes (id, cita_id, cliente_id, tipo, serie, numero, fecha, subtotal, igv, total, estado) VALUES
(1, 1, 1, 'BOLETA', 'B001', 1, '2025-10-24 08:55:00', 93.22, 16.78, 110.00, 'EMITIDO'),
(2, 2, 1, 'BOLETA', 'B001', 2, '2025-10-27 09:55:00', 93.22, 16.78, 110.00, 'EMITIDO'),
(3, 3, 1, 'BOLETA', 'B001', 3, '2025-10-31 10:55:00', 93.22, 16.78, 110.00, 'ANULADO'),
(4, 4, 1, 'BOLETA', 'B001', 4, '2025-11-03 10:35:00', 67.80, 12.20, 80.00, 'EMITIDO'),
(5, 5, 3, 'BOLETA', 'B001', 5, '2025-10-29 11:55:00', 93.22, 16.78, 110.00, 'EMITIDO'),
(6, 6, 3, 'BOLETA', 'B001', 6, '2025-11-05 12:55:00', 93.22, 16.78, 110.00, 'EMITIDO'),
(7, 7, 3, 'BOLETA', 'B001', 7, '2025-11-07 11:55:00', 88.98, 16.02, 105.00, 'EMITIDO'),
(8, 8, 2, 'BOLETA', 'B001', 8, '2025-10-26 15:05:00', 110.17, 19.83, 130.00, 'EMITIDO'),
(9, 9, 2, 'BOLETA', 'B001', 9, '2025-11-02 16:05:00', 110.17, 19.83, 130.00, 'EMITIDO'),
(10, 11, 5, 'BOLETA', 'B001', 10, '2025-11-08 12:55:00', 93.22, 16.78, 110.00, 'EMITIDO'),
(11, 12, 5, 'BOLETA', 'B001', 11, '2025-11-10 13:35:00', 67.80, 12.20, 80.00, 'EMITIDO'),
(12, 13, 6, 'BOLETA', 'B001', 12, '2025-11-11 08:50:00', 80.51, 14.49, 95.00, 'EMITIDO'),
(13, 14, 6, 'BOLETA', 'B001', 13, '2025-11-13 15:05:00', 101.69, 18.31, 120.00, 'EMITIDO'),
(14, 15, 8, 'BOLETA', 'B001', 14, '2025-11-15 13:55:00', 93.22, 16.78, 110.00, 'EMITIDO'),
(15, 17, 7, 'BOLETA', 'B001', 15, '2025-11-17 15:50:00', 80.51, 14.49, 95.00, 'EMITIDO'),
(16, 18, 1, 'BOLETA', 'B001', 16, '2025-11-19 10:50:00', 80.51, 14.49, 95.00, 'EMITIDO'),
(17, 19, 11, 'FACTURA', 'F001', 1, '2025-11-20 14:55:00', 88.98, 16.02, 105.00, 'EMITIDO'),
(18, 20, 12, 'BOLETA', 'B001', 17, '2025-11-21 09:05:00', 67.80, 12.20, 80.00, 'EMITIDO'),
(19, 22, 1, 'BOLETA', 'B001', 18, '2025-11-23 10:25:00', 93.22, 16.78, 110.00, 'EMITIDO');

-- Pagos
INSERT INTO pagos (id, comprobante_id, metodo, monto, fecha, nro_operacion) VALUES
(1, 1, 'YAPE', 110.00, '2025-10-24 08:56:00', 'YP100001'),
(2, 2, 'TARJETA', 110.00, '2025-10-27 09:56:00', 'TJ100002'),
(3, 4, 'EFECTIVO', 80.00, '2025-11-03 10:36:00', NULL),
(4, 5, 'EFECTIVO', 55.00, '2025-10-29 11:56:00', NULL),
(5, 5, 'YAPE', 55.00, '2025-10-29 11:56:00', 'YP100005'),
(6, 6, 'EFECTIVO', 110.00, '2025-11-05 12:56:00', NULL),
(7, 7, 'YAPE', 105.00, '2025-11-07 11:56:00', 'YP100007'),
(8, 8, 'TARJETA', 130.00, '2025-10-26 15:06:00', 'TJ100008'),
(9, 9, 'PLIN', 130.00, '2025-11-02 16:06:00', 'PL100009'),
(10, 10, 'EFECTIVO', 110.00, '2025-11-08 12:56:00', NULL),
(11, 11, 'YAPE', 80.00, '2025-11-10 13:36:00', 'YP100011'),
(12, 12, 'EFECTIVO', 95.00, '2025-11-11 08:51:00', NULL),
(13, 13, 'YAPE', 120.00, '2025-11-13 15:06:00', 'YP100013'),
(14, 14, 'TARJETA', 110.00, '2025-11-15 13:56:00', 'TJ100014'),
(15, 15, 'PLIN', 95.00, '2025-11-17 15:51:00', 'PL100015'),
(16, 16, 'EFECTIVO', 95.00, '2025-11-19 10:51:00', NULL),
(17, 17, 'YAPE', 105.00, '2025-11-20 14:56:00', 'YP100017'),
(18, 18, 'EFECTIVO', 80.00, '2025-11-21 09:06:00', NULL),
(19, 19, 'YAPE', 110.00, '2025-11-23 10:26:00', 'YP100019');

-- Auditoria
INSERT INTO auditoria (id, usuario_id, accion, tabla, registro_id, fecha) VALUES
(1, 3, 'LOGIN', NULL, NULL, '2025-11-22 08:00:00'),
(2, 3, 'CREAR', 'usuarios', 9, '2025-11-22 08:10:00'),
(3, 1, 'LOGIN', NULL, NULL, '2025-11-22 08:30:00'),
(4, 1, 'CREAR', 'servicios', 7, '2025-11-22 08:40:00'),
(5, 1, 'ACTUALIZAR', 'horarios', 4, '2025-11-22 09:00:00'),
(6, 1, 'CREAR', 'consultas', 8, '2025-11-20 10:00:00'),
(7, 10, 'LOGIN', NULL, NULL, '2025-11-23 07:55:00'),
(8, 10, 'ACTUALIZAR', 'citas', 22, '2025-11-23 10:30:00'),
(9, 10, 'CREAR', 'comprobantes', 19, '2025-11-23 10:35:00'),
(10, 4, 'LOGIN', NULL, NULL, '2025-11-23 08:05:00'),
(11, 4, 'ACTUALIZAR', 'terapistas', 1, '2025-11-23 08:07:00'),
(12, 13, 'LOGIN', NULL, NULL, '2025-11-23 07:30:00'),
(13, 3, 'ACTUALIZAR', 'usuarios', 22, '2025-11-23 09:00:00');
