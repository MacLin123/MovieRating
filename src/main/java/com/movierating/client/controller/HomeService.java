package com.movierating.client.controller;

import com.movierating.client.model.Movie;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("home")
public interface HomeService extends RestService {
    @GET
    @Path("new_releases")
    void getNewReleases(final MethodCallback<List<Movie>> callback);

    @GET
    @Path("upcoming_releases")
    void getUpcomingReleases(final MethodCallback<List<Movie>> callback);

    @GET
    @Path("best_movies/{year}")
    void getBestMoviesByYear(@PathParam("year") int year, final MethodCallback<List<Movie>> callback);
}
