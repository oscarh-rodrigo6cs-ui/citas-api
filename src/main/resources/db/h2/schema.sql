DROP ALL OBJECTS;

CREATE TABLE roles (
  id      BIGINT       NOT NULL AUTO_INCREMENT,
  nombre  VARCHAR(20)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_roles_nombre (nombre)
);

CREATE TABLE usuarios (
  id             BIGINT        NOT NULL AUTO_INCREMENT,
  email          VARCHAR(100)  NOT NULL,
  password       VARCHAR(60)   NOT NULL,
  rol_id         BIGINT        NOT NULL,
  activo         BOOLEAN       NOT NULL DEFAULT TRUE,
  creado_en      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_usuarios_email (email),
  CONSTRAINT fk_usuarios_rol FOREIGN KEY (rol_id) REFERENCES roles (id)
);

CREATE TABLE parametros (
  id           BIGINT        NOT NULL AUTO_INCREMENT,
  clave        VARCHAR(40)   NOT NULL,
  valor        VARCHAR(100)  NOT NULL,
  descripcion  VARCHAR(255)  NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_parametros_clave (clave)
);

CREATE TABLE auditoria (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  usuario_id   BIGINT       NOT NULL,
  accion       VARCHAR(20)  NOT NULL,
  tabla        VARCHAR(40)  NULL,
  registro_id  BIGINT       NULL,
  fecha        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT fk_auditoria_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE servicios (
  id           BIGINT        NOT NULL AUTO_INCREMENT,
  titulo       VARCHAR(80)   NOT NULL,
  descripcion  VARCHAR(255)  NULL,
  costo        DECIMAL(8,2)  NOT NULL,
  duracion     INT           NOT NULL,
  foto         VARCHAR(255)  NULL,
  activo       BOOLEAN       NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id),
  UNIQUE KEY uk_servicios_titulo (titulo),
  CONSTRAINT ck_servicios_costo CHECK (costo >= 0),
  CONSTRAINT ck_servicios_duracion CHECK (duracion BETWEEN 15 AND 180)
);

CREATE TABLE recepcionistas (
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  usuario_id  BIGINT       NOT NULL,
  nombres     VARCHAR(60)  NOT NULL,
  apellidos   VARCHAR(60)  NOT NULL,
  dni         VARCHAR(8)   NOT NULL,
  telefono    VARCHAR(15)  NULL,
  turno       VARCHAR(10)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_recepcionistas_usuario (usuario_id),
  UNIQUE KEY uk_recepcionistas_dni (dni),
  CONSTRAINT fk_recepcionistas_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE terapistas (
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  usuario_id  BIGINT       NOT NULL,
  nombres     VARCHAR(60)  NOT NULL,
  apellidos   VARCHAR(60)  NOT NULL,
  dni         VARCHAR(8)   NOT NULL,
  telefono    VARCHAR(15)  NULL,
  activo      BOOLEAN      NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id),
  UNIQUE KEY uk_terapistas_usuario (usuario_id),
  UNIQUE KEY uk_terapistas_dni (dni),
  CONSTRAINT fk_terapistas_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE horarios (
  id            BIGINT   NOT NULL AUTO_INCREMENT,
  terapista_id  BIGINT   NOT NULL,
  dia_semana    TINYINT  NOT NULL,
  hora_inicio   TIME     NOT NULL,
  hora_fin      TIME     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_horarios (terapista_id, dia_semana, hora_inicio),
  CONSTRAINT fk_horarios_terapista FOREIGN KEY (terapista_id) REFERENCES terapistas (id),
  CONSTRAINT ck_horarios_dia CHECK (dia_semana BETWEEN 1 AND 7),
  CONSTRAINT ck_horarios_horas CHECK (hora_inicio < hora_fin)
);

CREATE TABLE terapista_servicios (
  id            BIGINT  NOT NULL AUTO_INCREMENT,
  terapista_id  BIGINT  NOT NULL,
  servicio_id   BIGINT  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_terapista_servicio (terapista_id, servicio_id),
  CONSTRAINT fk_ts_terapista FOREIGN KEY (terapista_id) REFERENCES terapistas (id),
  CONSTRAINT fk_ts_servicio  FOREIGN KEY (servicio_id)  REFERENCES servicios (id)
);

CREATE TABLE clientes (
  id                BIGINT        NOT NULL AUTO_INCREMENT,
  usuario_id        BIGINT        NULL,
  nombres           VARCHAR(60)   NOT NULL,
  apellidos         VARCHAR(60)   NOT NULL,
  dni               VARCHAR(8)    NOT NULL,
  fecha_nacimiento  DATE          NOT NULL,
  telefono          VARCHAR(15)   NULL,
  correo            VARCHAR(100)  NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_clientes_usuario (usuario_id),
  UNIQUE KEY uk_clientes_dni (dni),
  CONSTRAINT fk_clientes_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE antecedentes (
  id           BIGINT        NOT NULL AUTO_INCREMENT,
  cliente_id   BIGINT        NOT NULL,
  fecha        DATE          NOT NULL,
  descripcion  VARCHAR(2000) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_antecedentes_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE consultas (
  id            BIGINT        NOT NULL AUTO_INCREMENT,
  cliente_id    BIGINT        NOT NULL,
  usuario_id    BIGINT        NOT NULL,
  fecha         DATE          NOT NULL,
  indicaciones  VARCHAR(500)  NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_consultas_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id),
  CONSTRAINT fk_consultas_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE consulta_servicios (
  id           BIGINT  NOT NULL AUTO_INCREMENT,
  consulta_id  BIGINT  NOT NULL,
  servicio_id  BIGINT  NOT NULL,
  sesiones     INT     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_consulta_servicio (consulta_id, servicio_id),
  CONSTRAINT fk_cs_consulta FOREIGN KEY (consulta_id) REFERENCES consultas (id),
  CONSTRAINT fk_cs_servicio FOREIGN KEY (servicio_id) REFERENCES servicios (id),
  CONSTRAINT ck_cs_sesiones CHECK (sesiones > 0)
);

CREATE TABLE citas (
  id                BIGINT       NOT NULL AUTO_INCREMENT,
  cliente_id        BIGINT       NOT NULL,
  terapista_id      BIGINT       NOT NULL,
  servicio_id       BIGINT       NOT NULL,
  recepcionista_id  BIGINT       NULL,
  hora_programada   DATETIME     NOT NULL,
  hora_inicio       DATETIME     NULL,
  hora_fin          DATETIME     NULL,
  estado            VARCHAR(12)  NOT NULL DEFAULT 'RESERVADA',
  valoracion        TINYINT      NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_citas_terapista_hora (terapista_id, hora_programada),
  CONSTRAINT fk_citas_cliente       FOREIGN KEY (cliente_id)       REFERENCES clientes (id),
  CONSTRAINT fk_citas_terapista     FOREIGN KEY (terapista_id)     REFERENCES terapistas (id),
  CONSTRAINT fk_citas_servicio      FOREIGN KEY (servicio_id)      REFERENCES servicios (id),
  CONSTRAINT fk_citas_recepcionista FOREIGN KEY (recepcionista_id) REFERENCES recepcionistas (id),
  CONSTRAINT ck_citas_valoracion CHECK (valoracion IS NULL OR valoracion BETWEEN 1 AND 5)
);

CREATE TABLE comprobantes (
  id          BIGINT        NOT NULL AUTO_INCREMENT,
  cita_id     BIGINT        NOT NULL,
  cliente_id  BIGINT        NOT NULL,
  tipo        VARCHAR(7)    NOT NULL,
  serie       VARCHAR(4)    NOT NULL,
  numero      INT           NOT NULL,
  fecha       DATETIME      NOT NULL,
  subtotal    DECIMAL(8,2)  NOT NULL,
  igv         DECIMAL(8,2)  NOT NULL,
  total       DECIMAL(8,2)  NOT NULL,
  estado      VARCHAR(10)   NOT NULL DEFAULT 'EMITIDO',
  PRIMARY KEY (id),
  UNIQUE KEY uk_comprobantes_serie_numero (serie, numero),
  CONSTRAINT fk_comprobantes_cita    FOREIGN KEY (cita_id)    REFERENCES citas (id),
  CONSTRAINT fk_comprobantes_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE pagos (
  id              BIGINT        NOT NULL AUTO_INCREMENT,
  comprobante_id  BIGINT        NOT NULL,
  metodo          VARCHAR(10)   NOT NULL,
  monto           DECIMAL(8,2)  NOT NULL,
  fecha           DATETIME      NOT NULL,
  nro_operacion   VARCHAR(30)   NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_pagos_comprobante FOREIGN KEY (comprobante_id) REFERENCES comprobantes (id),
  CONSTRAINT ck_pagos_monto CHECK (monto > 0)
);
