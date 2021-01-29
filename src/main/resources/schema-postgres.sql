DROP TABLE IF EXISTS movies;
CREATE TABLE IF NOT EXISTS movies
(
    id           bigserial PRIMARY key,
    title        VARCHAR(255),
    description  VARCHAR(2000),
    rating       INTEGER,
    genre        VARCHAR(255),
    premier_date DATE,
    cover_img BYTEA,
    medium_img BYTEA,
    large_img BYTEA
);

-- INSERT INTO movies(title, description, genre, premier_date) VALUES ('Citizen Kane', 'good film', 'Drama','1941-09-04');
-- INSERT INTO movies(title, description, genre, premier_date) VALUES ('The Godfather', 'good film', 'Drama','1972-03-11');