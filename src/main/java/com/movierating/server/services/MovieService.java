package com.movierating.server.services;

import com.movierating.server.dtos.MovieDtoMdImg;
import com.movierating.server.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {
    private final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDtoMdImg> get10NewMovieReleases() {
        Date start = getDateLastYear();
        List<MovieDtoMdImg> movieViews = movieRepository.findFirst10ByPremierDateBetween(start, new Date(), MovieDtoMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }

    public List<MovieDtoMdImg> getAllNewMovieReleases() {
        Date start = getDateLastYear();
        List<MovieDtoMdImg> movieViews = movieRepository.findByPremierDateBetween(start, new Date(), MovieDtoMdImg.class);
        logger.info("GET - " + movieViews);
        return movieViews;
    }

    private Date getDateLastYear() {
        int daysInYear = 365;
        if (Year.isLeap(Year.now().getLong(ChronoField.YEAR))) {
            daysInYear++;
        }
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(daysInYear));
        return Date.from(before);
    }
}
