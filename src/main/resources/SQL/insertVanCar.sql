INSERT INTO vehicle (brand, model, license_plate, color, year, price_per_day, fuel_type, quantity, status_vehicle, available)
VALUES ('Mercedes', 'Sprinter', 'VAN789', 'White', 2022, 79.50, 'Diesel', 2, 'ROLE_AVAILABLE', true);

-- Insert en la tabla hija 'van', usando el ID generado anteriormente
INSERT INTO van (id) VALUES (LAST_INSERT_ID());