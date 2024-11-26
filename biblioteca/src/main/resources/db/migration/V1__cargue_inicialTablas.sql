-- Tabla de Bibliotecas
CREATE TABLE libraries (
                           li_id INT AUTO_INCREMENT PRIMARY KEY,
                           li_name VARCHAR(100) NOT NULL,
                           li_location VARCHAR(255) NOT NULL,
                           li_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Libros
CREATE TABLE books (
                       bo_id INT AUTO_INCREMENT PRIMARY KEY,
                       bo_title VARCHAR(255) NOT NULL,
                       bo_author VARCHAR(255) NOT NULL,
                       bo_genre VARCHAR(100),
                       bo_library_id INT NOT NULL,
                       bo_is_available BOOLEAN DEFAULT TRUE,
                       bo_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (bo_library_id) REFERENCES libraries(li_id) ON DELETE CASCADE
);

-- Tabla de Miembros
CREATE TABLE members (
                         me_id INT AUTO_INCREMENT PRIMARY KEY,
                         me_first_name VARCHAR(100) NOT NULL,
                         me_last_name VARCHAR(100) NOT NULL,
                         me_email VARCHAR(255) UNIQUE NOT NULL,
                         me_phone VARCHAR(15),
                         me_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Préstamos
CREATE TABLE loans (
                       lo_id INT AUTO_INCREMENT PRIMARY KEY,
                       lo_book_id INT NOT NULL,
                       lo_member_id INT NOT NULL,
                       lo_loan_date DATE NOT NULL,
                       lo_return_date DATE,
                       lo_returned BOOLEAN DEFAULT FALSE,
                       lo_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (lo_book_id) REFERENCES books(bo_id) ON DELETE CASCADE,
                       FOREIGN KEY (lo_member_id) REFERENCES members(me_id) ON DELETE CASCADE
);

-- Poblar la tabla Libraries
INSERT INTO libraries (li_name, li_location) VALUES
('Central Library', '123 Main Street'),
('Downtown Library', '456 Elm Street'),
('Westside Library', '789 Oak Avenue');

-- Poblar la tabla Books
INSERT INTO books (bo_title, bo_author, bo_genre, bo_library_id, bo_is_available) VALUES
('1984', 'George Orwell', 'Dystopian', 1, TRUE),
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 1, TRUE),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 2, TRUE),
('Moby Dick', 'Herman Melville', 'Adventure', 2, TRUE),
('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 3, TRUE);

-- Poblar la tabla Members
INSERT INTO members (me_first_name, me_last_name, me_email, me_phone) VALUES
('Alice', 'Johnson', 'alice.johnson@example.com', '555-1234'),
('Bob', 'Smith', 'bob.smith@example.com', '555-5678'),
('Charlie', 'Brown', 'charlie.brown@example.com', '555-8765'),
('Diana', 'Prince', 'diana.prince@example.com', '555-4321');

-- Poblar la tabla Loans
INSERT INTO loans (lo_book_id, lo_member_id, lo_loan_date, lo_return_date, lo_returned) VALUES
(1, 1, '2024-11-20', NULL, FALSE),
(3, 2, '2024-11-18', '2024-11-23', TRUE),
(5, 3, '2024-11-19', NULL, FALSE);
