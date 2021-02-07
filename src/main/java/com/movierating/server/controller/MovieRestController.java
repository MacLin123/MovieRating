package com.movierating.server.controller;

import com.movierating.server.repository.MovieRepository;
import com.movierating.server.services.MovieService;
import com.movierating.server.dtos.MovieDtoLgImg;
import com.movierating.server.dtos.MovieDtoMdImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("movie")
public class MovieRestController {
    private final MovieRepository movieRepository;
    private final Logger logger = LoggerFactory.getLogger(MovieRestController.class);
    private final MovieService movieService;

    @Autowired
    public MovieRestController(MovieRepository movieRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MovieDtoLgImg getMovie(@PathVariable("id") Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "movie not found");
        }
        logger.info("GET details movie - id = " + id);
        return movieRepository.getById(id, MovieDtoLgImg.class);
    }

    @RequestMapping(value = "genre/{genre}", method = RequestMethod.GET)
    public List<MovieDtoMdImg> getMoviesByGenre(@PathVariable("genre") String genre) {
        logger.info("GET movie by genre = " + genre);
        String[] genres = genre.split(",");
        return movieRepository.findFirst10ByGenreContainingIgnoreCase(genres[0].trim(), MovieDtoMdImg.class);
    }

    @RequestMapping(value = "new_releases", method = RequestMethod.GET)
    public List<MovieDtoMdImg> getNewReleasesMovies() {
        return movieService.getAllNewMovieReleases();
    }

    @RequestMapping(value = "upcoming_releases", method = RequestMethod.GET)
    public List<MovieDtoMdImg> getUpcomingReleases() {
        List<MovieDtoMdImg> movieViews = movieRepository.findByPremierDateAfter(new Date(), MovieDtoMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }
}
