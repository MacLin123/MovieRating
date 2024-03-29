package com.movierating.server.controller;

import com.movierating.server.dtos.MovieDtoLgImg;
import com.movierating.server.dtos.MovieDtoSmImg;
import com.movierating.server.model.Movie;
import com.movierating.server.repository.MovieRepository;
import com.movierating.server.utils.DateUtils;
import com.movierating.server.utils.ImageUtils;
import com.movierating.server.utils.MoviesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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
    public List<MovieDtoSmImg> getSearchedMovies(@RequestBody String title) {
        logger.info("Search querry");
        List<MovieDtoSmImg> movieViews = movieRepository.findByTitleContainingIgnoreCase(title, MovieDtoSmImg.class);
        logger.info(movieViews.toString());
        return movieViews;
    }

    @RequestMapping(value = "movies/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long id) {
        if (!movieRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body("movie not found");
        }
        movieRepository.deleteById(id);
        logger.info("movie with id = " + id + " has been deleted");
        return ResponseEntity.ok()
                .body("movie has been deleted");
    }

    @RequestMapping(value = "movies/update/{id}", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> updateMovie(@PathVariable("id") Long id, @RequestParam MultipartFile file,
                                              @RequestParam String date, @RequestParam String title,
                                              @RequestParam String description, @RequestParam String genre) throws IOException {
        if (!movieRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body("movie not found");
        }

        if ((!file.isEmpty()) && (!isValidFileExt(file))) {
            return ResponseEntity.badRequest()
                    .body("image extension is not valid");
        }

        byte[] imgBytes = null;
        if (file.isEmpty()) {
            imgBytes = movieRepository.findById(id).get().getCoverImg();
        } else {
            imgBytes = file.getBytes();
        }

        Movie movie = movieRepository.getOne(id);

        movie.setTitle(title);
        movie.setDescription(description);
        movie.setGenre(genre);
        movie.setPremierDate(DateUtils.convertStringToDate(date));
        movie.setCoverImg(imgBytes);

        movieRepository.save(movie);
        logger.info("updated - " + movie);
        return ResponseEntity.ok()
                .body("movie has been updated");
    }

    @RequestMapping(value = "movies/{id}", method = RequestMethod.GET)
    public MovieDtoLgImg getMovie(@PathVariable("id") Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "movie not found");
        }
        logger.info("GET - id = " + id);
        return movieRepository.getById(id, MovieDtoLgImg.class);
    }

    @RequestMapping(value = "movies/create", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> createMovie(@RequestParam MultipartFile file,
                                              @RequestParam String date, @RequestParam String title,
                                              @RequestParam String description, @RequestParam String genre,
                                              @RequestParam String youtubeId) throws IOException {
        if (movieRepository.existsByTitleIgnoreCase(title)) {
            return ResponseEntity.badRequest()
                    .body("the movie you want to add already exists");
        }

        if ((!file.isEmpty()) && (!isValidFileExt(file))) {
            return ResponseEntity.badRequest()
                    .body("image extension is not valid");
        }

        Movie movie = MoviesUtils.createMovie(file.getBytes(), title, description, genre, DateUtils.convertStringToDate(date), youtubeId);
        movieRepository.save(movie);
        logger.info("saved - " + movie);
        return ResponseEntity.ok()
                .body("movie has been saved");
    }

    private boolean isValidFileExt(MultipartFile file) {
        String extension = ImageUtils.getExtensionByStringHandling(
                file.getOriginalFilename()).get();
        if ("jpeg".equals(extension) || "png".equals(extension) ||
                "jpg".equals(extension) || "gif".equals(extension)) {
            return true;
        }
        return false;
    }
}
