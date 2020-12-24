package com.movierating.server.controller;

import com.movierating.server.model.Movie;
import com.movierating.server.model.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("admin")
public class AdminRestController {
    private final MovieRepository movieRepository;

    private final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    public AdminRestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ResponseBody
    @RequestMapping(value = "movies", method = RequestMethod.POST)
    public List<Movie> getRecentMovies(@RequestBody String title){
        logger.info("Search querry");
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        logger.info(movies.toString());
        return movies;
    }

    @RequestMapping(value = "movies", method = RequestMethod.DELETE)
    void deleteMovie(@RequestBody final Movie movie){
        movieRepository.delete(movie);
    }
}
