-- Dodanie użytkowników
INSERT INTO users (username, password, password_hash, email, role) 
VALUES 
('test_user', 'hashed_password_1', 'hashed_password_1', 'user1@example.com', 'ADMIN'),
('demo_user', 'hashed_password_2', 'hashed_password_2', 'user2@example.com', 'USER');

-- Dodanie plików
INSERT INTO file (user_id, file_name, file_path, size, content) 
VALUES 
(1, 'file1.txt', '/uploads/file1.txt', 1024, '\\x'),
(1, 'file2.txt', '/uploads/file2.txt', 2048, '\\x'),
(2, 'file3.txt', '/uploads/file3.txt', 512, '\\x');

-- Dodanie logów
INSERT INTO logs (user_id, action) 
VALUES 
(1, 'User login'),
(2, 'File upload'),
(1, 'File deletion');
