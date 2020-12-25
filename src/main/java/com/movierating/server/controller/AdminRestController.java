package com.movierating.server.controller;

import com.movierating.server.exceptions.movie.MovieIsAlreadyExists;
import com.movierating.server.model.Movie;
import com.movierating.server.model.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Movie> getSearchedMovies(@RequestBody String title) {
        logger.info("Search querry");
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        logger.info(movies.toString());
        return movies;
    }

    @RequestMapping(value = "movies", method = RequestMethod.DELETE)
    void deleteMovie(@RequestBody final Movie movie) {
        movieRepository.delete(movie);
    }

    @RequestMapping(value = "movies", method = RequestMethod.PUT)
    void addMovie(@RequestBody final Movie movie) {
        List<Movie> movies = movieRepository.findByTitleIgnoreCase(movie.getTitle());
        if ((movies != null) && (!movies.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "the movie you want to add already exists");
        }
        movieRepository.save(movie);
        logger.info("saved - " + movie);
    }

    @RequestMapping(value = "movies/{id}", method = RequestMethod.POST)
    void updateMovie(@PathVariable("id") Long id,@RequestBody final Movie movie) {
        if (!movieRepository.existsById(movie.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "movie not found");
        }
        movieRepository.save(movie);
        logger.info("updated - " + movie);
    }
}
