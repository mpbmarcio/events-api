INSERT INTO tbl_user (user_name, user_email, user_password)
VALUES
('Márcio', 'mpbmarcio@hotmail.com', '$2a$10$jC/ktwUTyxj.ccndf1qFqOz0IMrPakZHy0q7eOMR/1jCUy4z1ZRZq'),
('Ana Souza', 'ana.souza@example.com', '$2a$10$wz7BkbdHvSvCCDhmMp1G6uDmhVbbz11dGmHZKoCeoFR9JN7y0RH8.'),
('Carlos Pereira', 'carlos.pereira@example.com', '$2a$10$aLqHlDdGzO4UTYVJ6Kv28.o8pR4VO8ZcrBl7f0/6Nl7c1UflnTzVK');

INSERT INTO tbl_conference (name, address)
VALUES
('Tech Summit 2026', 'Av. Paulista, 1000 - São Paulo, SP'),
('Data Science Conference', 'Rua das Flores, 250 - Rio de Janeiro, RJ'),
('AI & Innovation Forum', 'Praça da Liberdade, 45 - Belo Horizonte, MG');

INSERT INTO tbl_session (title, start_date, start_time, conference_id)
VALUES
('Introdução ao Machine Learning', '2026-05-10', '09:00:00', 1),
('Tendências em Big Data', '2026-05-10', '11:00:00', 1),
('Deep Learning Avançado', '2026-06-15', '10:30:00', 2),
('Ética na Inteligência Artificial', '2026-06-15', '14:00:00', 2),
('Startups e Inovação Tecnológica', '2026-07-20', '09:30:00', 3);

INSERT INTO tbl_subscription (user_id, session_id, level, unique_id)
VALUES
(1, 1, 1, 'SUB-001-USER1'),
(1, 2, 2, 'SUB-002-USER1'),
(2, 3, 1, 'SUB-003-USER2'),
(3, 4, 3, 'SUB-004-USER3'),
(2, 5, 2, 'SUB-005-USER2');