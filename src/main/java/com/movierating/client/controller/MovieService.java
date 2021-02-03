package com.movierating.client.controller;

import com.movierating.client.model.Movie;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("movie")
public
interface MovieService extends RestService {
    @GET
    @Path("/{id}")
    void getMovieDetails(@PathParam("id") Long id, final MethodCallback<Movie> callback);

    @GET
    @Path("/genre/{genre}")
    void getMoviesByGenre(@PathParam("genre") String genre, final MethodCallback<List<Movie>> callback);
}
