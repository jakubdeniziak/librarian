CREATE TABLE authors (
    id CHAR(36) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE publishers (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    website_url VARCHAR(255),
    description TEXT
);

CREATE TABLE books (
    id CHAR(36) PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    author_id CHAR(36),
    publisher_id CHAR(36),
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE SET NULL,
    CONSTRAINT fk_publisher FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE SET NULL
);

CREATE TABLE libraries (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE library_books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    library_id CHAR(36),
    book_id CHAR(36),
    number_of_copies INT NOT NULL,
    CONSTRAINT fk_library FOREIGN KEY (library_id) REFERENCES libraries(id) ON DELETE CASCADE,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);
