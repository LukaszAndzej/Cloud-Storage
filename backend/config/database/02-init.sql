-- Przyklad dodania użytkowników
INSERT INTO users (username, password_hash, email) 
VALUES 
('test_user', 'hashed_password_1', 'user1@example.com'),
('demo_user', 'hashed_password_2', 'user2@example.com');

-- Przyklad dodania pliki
INSERT INTO files (user_id, file_name, file_path) 
VALUES 
(1, 'file1.txt', '/uploads/file1.txt'),
(1, 'file2.txt', '/uploads/file2.txt'),
(2, 'file3.txt', '/uploads/file3.txt');

-- Przyklad dodania logi
INSERT INTO logs (user_id, action) 
VALUES 
(1, 'User login'),
(2, 'File upload'),
(1, 'File deletion');
