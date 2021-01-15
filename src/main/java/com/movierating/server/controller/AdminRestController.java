package com.movierating.server.controller;

import com.movierating.server.model.Movie;
import com.movierating.server.model.MovieRepository;
import com.movierating.server.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

;

@RestController()
@RequestMapping("admin")
public class AdminRestController {
    private final MovieRepository movieRepository;

    private final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
    private HttpHeaders responseHeaders;

    @Autowired
    public AdminRestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Frame-Options",
                "SAMEORIGIN");
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
        if (movieRepository.existsByTitleIgnoreCase(movie.getTitle())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "the movie you want to add already exists");
        }
        movieRepository.save(movie);
        logger.info("saved - " + movie);
    }

    @RequestMapping(value = "movies/{id}", method = RequestMethod.POST)
    void updateMovie(@PathVariable("id") Long id, @RequestBody final Movie movie) {
        if (!movieRepository.existsById(movie.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "movie not found");
        }
        movieRepository.save(movie);
        logger.info("updated - " + movie);
    }

    @RequestMapping(value = "movies2", method = RequestMethod.POST)
    ResponseEntity<String> createMovie2(@RequestParam MultipartFile file,
                                        @RequestParam String date, @RequestParam String title,
                                        @RequestParam String description, @RequestParam String genre) throws IOException {
        if (movieRepository.existsByTitleIgnoreCase(title)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "the movie you want to add already exists");
        }

        if (file.getSize() > (1024 * 1024 * 5)) {
            return ResponseEntity.badRequest()
                    .headers(responseHeaders)
                    .body("max size of file is 5MB");
        }

        Movie movie = new Movie(title, description, genre, DateUtils.convertStringToDate(date), file.getBytes());
        logger.info("saved" + movie);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("movie has been saved");
    }
}
