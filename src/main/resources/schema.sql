CREATE TABLE IF NOT EXISTS monedas (
    idtipocambio INT AUTO_INCREMENT PRIMARY KEY,
    monedaorigen VARCHAR(3) NOT NULL,
    monedadestino VARCHAR(3) NOT NULL,
    equivalencia DECIMAL(10,3) NOT NULL
);

-- Datos iniciales para pruebas
INSERT INTO monedas (monedaorigen, monedadestino, equivalencia) VALUES
('USD', 'PEN', 3.700),
('PEN', 'USD', 0.270)
--('EUR', 'PEN', 4.100),
--('PEN', 'EUR', 0.244);