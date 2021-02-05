package com.movierating.server.controller;

import com.movierating.server.repository.MovieRepository;
import com.movierating.server.services.MovieService;
import com.movierating.server.views.MovieViewLgImg;
import com.movierating.server.views.MovieViewMdImg;
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
    public MovieViewLgImg getMovie(@PathVariable("id") Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "movie not found");
        }
        logger.info("GET details movie - id = " + id);
        return movieRepository.getById(id, MovieViewLgImg.class);
    }

    @RequestMapping(value = "genre/{genre}", method = RequestMethod.GET)
    public List<MovieViewMdImg> getMoviesByGenre(@PathVariable("genre") String genre) {
        logger.info("GET movie by genre = " + genre);
        String[] genres = genre.split(",");
        return movieRepository.findFirst10ByGenreContainingIgnoreCase(genres[0].trim(), MovieViewMdImg.class);
    }

    @RequestMapping(value = "new_releases", method = RequestMethod.GET)
    public List<MovieViewMdImg> getNewReleasesMovies() {
//        int daysInYear = 365;
//        if (Year.isLeap(Year.now().getLong(ChronoField.YEAR))) {
//            daysInYear++;
//        }
//        Instant now = Instant.now();
//        Instant before = now.minus(Duration.ofDays(daysInYear));
//        Date start = Date.from(before);
//        List<MovieViewMdImg> movieViews = movieRepository.findByPremierDateBetween(start, new Date(), MovieViewMdImg.class);
//        logger.info("GET - " + movieViews);
//        return movieViews;
        return movieService.getAllNewMovieReleases();
    }

    @RequestMapping(value = "upcoming_releases", method = RequestMethod.GET)
    public List<MovieViewMdImg> getUpcomingReleases() {
        List<MovieViewMdImg> movieViews = movieRepository.findByPremierDateAfter(new Date(), MovieViewMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }
}
