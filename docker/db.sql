CREATE DATABASE IF NOT EXISTS db_app DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE db_app;

SET NAMES utf8mb4;

create table book
(
    archived           boolean      not null,
    created_by         integer      not null,
    id                 serial
        primary key,
    last_modified_by   integer,
    owner_id           integer
        constraint fk61m8am98w4y4vgpl82sojy8bh
            references _user,
    shareable          boolean      not null,
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6),
    author_name        varchar(255),
    book_cover         varchar(255),
    isbn               varchar(255),
    synopsis           varchar(255),
    title              varchar(255)
);

alter table book
    owner to postgres;

-- Instancia 2
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (1, false, 1, 1, true, '2024-04-30 12:00:00', 'Autor 1', 'cover2.jpg', '321564987', 'Una breve sinopsis del libro 1', 'Título del Libro 1');

-- Instancia 3
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (2, false, 1, 1, true, '2024-04-30 12:00:00', 'Autor 2', 'cover2.jpg', '321987654', 'Una breve sinopsis del libro 2', 'Título del Libro 2');

-- Instancia 4
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (3, false, 1, 1, true, '2024-04-30 12:00:00', 'Autor 3', 'cover2.jpg', '987654951', 'Una breve sinopsis del libro 3', 'Título del Libro 3');

-- Instancia 5
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (4, false, 2, 2, true, '2024-04-30 12:00:00', 'Autor 2', 'cover2.jpg', '123654789', 'Una breve sinopsis del libro 4', 'Título del Libro 4');

-- Instancia 2
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (5, false, 2, 2, true, '2024-04-30 12:00:00', 'Autor 1', 'cover2.jpg', '215487963', 'Una breve sinopsis del libro 5', 'Título del Libro 5');

-- Instancia 3
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (6, false, 2, 2, true, '2024-04-30 12:00:00', 'Autor 3', 'cover2.jpg', '159263487', 'Una breve sinopsis del libro 6', 'Título del Libro 6');

-- Instancia 4
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (7, false, 2, 2, true, '2024-04-30 12:00:00', 'Autor 3', 'cover2.jpg', '732648159', 'Una breve sinopsis del libro 7', 'Título del Libro 7');

-- Instancia 5
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (8, false, 2, 2, true, '2024-04-30 12:00:00', 'Autor 2', 'cover2.jpg', '987654321', 'Una breve sinopsis del libro 8', 'Título del Libro 8');

-- Instancia 6
INSERT INTO book (id, archived, created_by, owner_id, shareable, created_date, author_name, book_cover, isbn, synopsis, title)
VALUES (9, false, 2, 2, true, '2024-04-30 12:00:00', 'Autor 2', 'cover2.jpg', '174258369', 'Una breve sinopsis del libro 9', 'Título del Libro 9');