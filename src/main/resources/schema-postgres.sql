CREATE TABLE IF NOT EXISTS movies
(
    id           bigserial PRIMARY key,
    title        VARCHAR(255),
    description  VARCHAR(500),
    rating       INTEGER,
    genre        VARCHAR(255),
    premier_date DATE,
    cover_img    BYTEA,
    medium_img   BYTEA,
    large_img    BYTEA,
    youtube_id   VARCHAR (255)
);