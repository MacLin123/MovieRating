package com.movierating.client.controller;

import com.movierating.client.model.Movie;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("admin")
public
interface AdminService extends RestService {
//    @GET
//    void getPage(MethodCallback<Void> callback);


//    @GET
//    @Path("movies?amount={amount}")
//    void getRecentMovies(String amount , MethodCallback<List<Movie>> callback);

    @POST
    @Path("movies")
    void searchMovies(String title, MethodCallback<List<Movie>> callback);

    @DELETE
    @Path("movies")
    void deleteMovie(final Movie movie, final MethodCallback<Void> callback);

    @PUT
    @Path("movies")
    void addMovie(final Movie movie, final MethodCallback<Void> callback);
}