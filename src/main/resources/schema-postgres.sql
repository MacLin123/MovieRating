DROP TABLE IF EXISTS movies;
CREATE TABLE movies
(
    id           serial PRIMARY key,
    title        VARCHAR(255),
    description  VARCHAR(255),
    rating       INTEGER,
    genre        VARCHAR(255),
    premier_date DATE
);

INSERT INTO movies(title, description, genre, premier_date) VALUES ('Citizen Kane', 'good film', 'Drama','1941-09-04');