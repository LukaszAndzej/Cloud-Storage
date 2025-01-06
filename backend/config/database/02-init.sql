-- Przyklad dodania użytkowników
INSERT INTO users (username, password, email, role) 
VALUES 
('test_user', 'hashed_password_1', 'user1@example.com', 'ADMIN'),
('demo_user', 'hashed_password_2', 'user2@example.com', 'USER');

-- Przyklad dodania plików
INSERT INTO file (user_id, file_name, file_path) 
VALUES 
(1, 'file1.txt', '/uploads/file1.txt'),
(1, 'file2.txt', '/uploads/file2.txt'),
(2, 'file3.txt', '/uploads/file3.txt');

-- Przyklad dodania logów
INSERT INTO logs (user_id, action) 
VALUES 
(1, 'User login'),
(2, 'File upload'),
(1, 'File deletion');
