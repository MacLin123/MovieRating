package com.movierating.server.controller;

import com.movierating.server.repository.MovieRepository;
import com.movierating.server.utils.DateUtils;
import com.movierating.server.views.MovieViewMdImg;
import com.movierating.server.views.MovieViewSmImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("home")
public class HomeRestController {

    private final MovieRepository movieRepository;
    private final Logger logger = LoggerFactory.getLogger(HomeRestController.class);

    @Autowired
    public HomeRestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "new_releases", method = RequestMethod.GET)
    public List<MovieViewMdImg> getNewReleasesMovies() {
        int daysInYear = 365;
        if (Year.isLeap(Year.now().getLong(ChronoField.YEAR))) {
            daysInYear++;
        }
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(daysInYear));
        Date start = Date.from(before);
        List<MovieViewMdImg> movieViews = movieRepository.findFirst10ByPremierDateBetween(start, new Date(), MovieViewMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }

    @RequestMapping(value = "upcoming_releases", method = RequestMethod.GET)
    public List<MovieViewMdImg> getUpcomingReleases() {
        List<MovieViewMdImg> movieViews = movieRepository.findFirst10ByPremierDateAfter(new Date(), MovieViewMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }

    @RequestMapping(value = "best_movies/{year}", method = RequestMethod.GET)
    public List<MovieViewSmImg> getBestMoviesByYear(@PathVariable("year") int year) {

        Date start = DateUtils.convertStringToDate(year + "-01-01");
        Date end = DateUtils.convertStringToDate(year + "-12-31");

        List<MovieViewSmImg> movieViews = movieRepository.
                findFirst10BestMovies(start, end, PageRequest.of(0, 10));
        logger.info("GET - " + movieViews);
        return movieViews;
    }
}
