package com.movierating.server.controller;

import com.movierating.server.repository.MovieRepository;
import com.movierating.server.views.MovieViewMdImgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
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
    List<MovieViewMdImgDto> getNewReleasesMovies() {
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(365));
        Date start = Date.from(before);

        List<MovieViewMdImgDto> movieViews = movieRepository.findFirst10ByPremierDateBetween(start, new Date(), MovieViewMdImgDto.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }

    @RequestMapping(value = "upcoming_releases", method = RequestMethod.GET)
    List<MovieViewMdImgDto> getUpcomingReleases() {
/*        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "movie not found");
        }
        logger.info("GET - id = " + id);
        return movieRepository.getById(id, MovieViewMdImgDto.class);*/
//        Instant now = Instant.now();
//        Instant before = now.plus(Duration.ofDays(365));
//        Date start = Date.from(before);

        List<MovieViewMdImgDto> movieViews = movieRepository.findFirst5ByPremierDateAfter(new Date(), MovieViewMdImgDto.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }
}
