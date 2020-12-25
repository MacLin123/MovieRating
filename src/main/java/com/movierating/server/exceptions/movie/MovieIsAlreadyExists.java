package com.movierating.server.exceptions.movie;

public class MovieIsAlreadyExists extends RuntimeException {
    public MovieIsAlreadyExists(String cause) {
        super(cause);
    }
}
