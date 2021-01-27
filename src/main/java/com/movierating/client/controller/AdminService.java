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
    @Path("movies/remove/{id}")
    void deleteMovie(@PathParam("id") Long id, final MethodCallback<Void> callback);

//    @PUT
//    @Path("movies")
//    void addMovie(final Movie movie, final MethodCallback<Void> callback);

//    @POST
//    @Path("movies/update/{id}")
//    void updateMovie(@PathParam("id") Long id, final Movie movie, final MethodCallback<Void> callback);

    @GET
    @Path("movies/{id}")
    void getMovie(@PathParam("id") Long id, final MethodCallback<Movie> callback);

}
