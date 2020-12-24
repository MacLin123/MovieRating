DROP TABLE IF EXISTS movie;
CREATE TABLE movie(
    id serial PRIMARY key,
    title VARCHAR (255),
    description VARCHAR (255),
    rating INTEGER,
    genre VARCHAR (255)
    );