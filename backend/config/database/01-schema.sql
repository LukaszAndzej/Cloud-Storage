CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    password_hash TEXT,
    email VARCHAR(255) UNIQUE,
    role VARCHAR(50) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE file (
    id SERIAL PRIMARY KEY,
    file_name VARCHAR(255),
    file_path VARCHAR(255),
    content BYTEA,
    size BIGINT,
    user_id BIGINT REFERENCES users(id),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE logs (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_file_user_id ON file(user_id);
CREATE INDEX idx_logs_user_id ON logs(user_id);
