package com.movierating.server.controller;

import com.movierating.server.repository.MovieRepository;
import com.movierating.server.services.MovieService;
import com.movierating.server.utils.DateUtils;
import com.movierating.server.dtos.MovieDtoMdImg;
import com.movierating.server.dtos.MovieDtoSmImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("home")
public class HomeRestController {

    private final MovieRepository movieRepository;
    private final Logger logger = LoggerFactory.getLogger(HomeRestController.class);
    private final MovieService movieService;

    @Autowired
    public HomeRestController(MovieRepository movieRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @RequestMapping(value = "new_releases", method = RequestMethod.GET)
    public List<MovieDtoMdImg> getNewReleasesMovies() {
        return movieService.get10NewMovieReleases();
    }

    @RequestMapping(value = "upcoming_releases", method = RequestMethod.GET)
    public List<MovieDtoMdImg> getUpcomingReleases() {
        List<MovieDtoMdImg> movieViews = movieRepository.findFirst10ByPremierDateAfter(new Date(), MovieDtoMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }

    @RequestMapping(value = "best_movies/{year}", method = RequestMethod.GET)
    public List<MovieDtoSmImg> getBestMoviesByYear(@PathVariable("year") int year) {

        Date start = DateUtils.convertStringToDate(year + "-01-01");
        Date end = DateUtils.convertStringToDate(year + "-12-31");

        List<MovieDtoSmImg> movieViews = movieRepository.
                findFirst10BestMovies(start, end, PageRequest.of(0, 10));
        logger.info("GET - " + movieViews);
        return movieViews;
    }
}
