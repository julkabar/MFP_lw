INSERT INTO books (title, author, pub_year) VALUES ('The Way of Kings', 'Brandon Sanderson', 2010);
INSERT INTO books (title, author, pub_year) VALUES ('The Name of the Wind', 'Patrick Rothfuss', 2007);
INSERT INTO books (title, author, pub_year) VALUES ('Elantris', 'Brandon Sanderson', 2005);
INSERT INTO books (title, author, pub_year) VALUES ('The Dragonbone Chair', 'Tad Williams', 1988);
INSERT INTO books (title, author, pub_year) VALUES ('Ship of Magic', 'Robin Hobb', 1998);

INSERT INTO comments (book_id, author, text, created_at)
VALUES (1, 'Yuliia', '!', CURRENT_TIMESTAMP);

INSERT INTO comments (book_id, author, text, created_at)
VALUES (1, 'Alice', '?', CURRENT_TIMESTAMP);

INSERT INTO comments (book_id, author, text, created_at)
VALUES (2, 'Yuliia', '!!', CURRENT_TIMESTAMP);

INSERT INTO comments (book_id, author, text, created_at)
VALUES (3, 'Alice', '???', CURRENT_TIMESTAMP);

INSERT INTO comments (book_id, author, text, created_at)
VALUES (3, 'Stefan', '...', CURRENT_TIMESTAMP);

INSERT INTO comments (book_id, author, text, created_at)
VALUES (4, 'Stefan', '....', CURRENT_TIMESTAMP);