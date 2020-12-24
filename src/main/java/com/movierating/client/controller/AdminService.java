package com.movierating.client.controller;

import com.movierating.client.model.Movie;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("admin")
public
interface AdminService extends RestService {
//    @GET
//    void getPage(MethodCallback<Void> callback);


//     Move to movies
    @GET
    @Path("movies?amount={amount}")
    void getRecentMovies(String amount , MethodCallback<List<Movie>> callback);

    @DELETE
    void deleteMovie(final Movie movie, final MethodCallback<Void> callback);

    @PUT
    void addMovie(final Movie movie, final MethodCallback<Void> callback);
}
