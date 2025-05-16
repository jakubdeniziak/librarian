CREATE TABLE authors (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE publishers (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    website_url VARCHAR(100),
    description VARCHAR(1000)
);

CREATE TABLE books (
    id UUID PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    author_id UUID REFERENCES authors(id),
    publisher_id UUID REFERENCES publishers(id)
);

CREATE TABLE libraries (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE library_books (
    library_id UUID REFERENCES libraries(id),
    book_id UUID REFERENCES books(id),
    number_of_copies INT NOT NULL,
    PRIMARY KEY(library_id, book_id)
);
