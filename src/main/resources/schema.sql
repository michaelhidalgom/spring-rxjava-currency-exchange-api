-- Tabla de monedas (ya existente)
CREATE TABLE IF NOT EXISTS monedas (
    idtipocambio INT AUTO_INCREMENT PRIMARY KEY,
    monedaorigen VARCHAR(3) NOT NULL,
    monedadestino VARCHAR(3) NOT NULL,
    equivalencia DECIMAL(10,3) NOT NULL
);

-- Tabla de roles
CREATE TABLE IF NOT EXISTS roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    authority VARCHAR(60) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL
);

-- Tabla de relación entre usuarios y roles
CREATE TABLE IF NOT EXISTS usuarios_roles (
    usuario_id INT NOT NULL,
    rol_id INT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Datos iniciales para pruebas de monedas
INSERT INTO monedas (monedaorigen, monedadestino, equivalencia) VALUES
('USD', 'PEN', 3.700),
('PEN', 'USD', 0.270);

-- Datos iniciales para roles
INSERT INTO roles (authority) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

-- USUARIOS DE PRUEBA
-- Usuario 1: admin/admin123
INSERT INTO usuarios (username, password, email, enabled) VALUES
('admin', '$2a$10$rPiEAgSYLPJyaQ3QmgEKi.JvYP9qejRmaMNA/gkn2mF2qbnGcQI9.', 'admin@example.com', true);

-- Usuario 2: user/user123
INSERT INTO usuarios (username, password, email, enabled) VALUES
('user', '$2a$10$Vz8NjGoVTPk94QSOVrUdJeUr33QlH9Rc.Ow8SPGcb8mQHFPqbHKVG', 'user@example.com', true);

-- Asignar roles a usuarios
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES
(1, 1), -- admin tiene rol USER
(1, 2), -- admin tiene rol ADMIN
(2, 1); -- user tiene rol USER
