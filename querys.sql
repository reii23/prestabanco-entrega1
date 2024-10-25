
-- Insert into loan_type table
INSERT INTO loan_type (name, max_term_years, min_interest_rate, max_interest_rate, max_financing_percentage) 
VALUES 
    ('Primera Vivienda', 30, 3.5, 5.0, 80.0),
    ('Segunda Vivienda', 20, 4.0, 6.0, 70.0),
    ('Propiedades Comerciales', 25, 5.0, 7.0, 60.0),
    ('Remodelación', 15, 4.5, 6.0, 50.0);

-- Insert into clients table
INSERT INTO clients (rut, name, age, salary, email) 
VALUES 
    ('12345678-9', 'Juan Pérez', 35, 5000000, 'juan.perez@example.com'),
    ('98765432-1', 'María López', 42, 6200000, 'maria.lopez@example.com'),
    ('45678912-3', 'Carlos González', 29, 4300000, 'carlos.gonzalez@example.com'),
    ('32165498-7', 'Ana Ramírez', 38, 7500000, 'ana.ramirez@example.com'),
    ('65498732-4', 'Pedro Martínez', 45, 8400000, 'pedro.martinez@example.com');
