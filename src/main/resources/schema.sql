-- Tabla de monedas
CREATE TABLE IF NOT EXISTS monedas (
    idtipocambio INT AUTO_INCREMENT PRIMARY KEY,
    monedaorigen VARCHAR(3) NOT NULL,
    monedadestino VARCHAR(3) NOT NULL,
    equivalencia DECIMAL(10,3) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL
);

-- Datos iniciales para pruebas de monedas
INSERT INTO monedas (monedaorigen, monedadestino, equivalencia) VALUES
('USD', 'PEN', 3.700),
('PEN', 'USD', 0.270);

-- USUARIOS DE PRUEBA
-- username:luis / password:luis123
INSERT INTO usuarios (username, password, email, enabled) VALUES
('luis', '$2a$10$biNjUd2t8f2PXlcD7txFi.nO0nCtWXzPhJyYMmOMK5z.BrTXpg4sm', 'luis@example.com', true);

-- username:marisol / password:mari123
INSERT INTO usuarios (username, password, email, enabled) VALUES
('marisol', '$2a$10$yOuDJ4Hpio3Y1KD6hYJEZOWJidy7PWXPO8r.AjGsG7CvMLH4E4STu', 'marisol@example.com', true);