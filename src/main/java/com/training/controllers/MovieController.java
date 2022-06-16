package com.training.controllers;

import com.training.exceptions.InvalidFileTypeException;
import com.training.exceptions.MovieResourceNotFoundException;
import com.training.models.Movie;
import com.training.service.CSVHelper;
import com.training.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MovieService movieService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("upload file");

        if (CSVHelper.hasCSVFormat(file)) {
            movieService.uploadAllMovie(file);
            String message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        throw new InvalidFileTypeException("Invalid file type - Upload csv file");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable("id") String movieId) throws MovieResourceNotFoundException {
        logger.info("getMovieById!");

        Movie movie = movieService.findMovieById(movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllMovies() {
        logger.info("getAllMovies!");

        List<Movie> optMovie = movieService.getAllMovies();
        return new ResponseEntity<>(optMovie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addNewMovie(@RequestBody Movie movie) {
        logger.info("add new movie!");
        Movie savedMovie = movieService.addNewMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public String updateMovie(@PathVariable(value = "id") String id,
                              @RequestBody Movie book) throws MovieResourceNotFoundException {
        logger.info("update movie {} ", this.getClass().getName());
        return movieService.update(id, book);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteMovie(@PathVariable("id") String id) throws MovieResourceNotFoundException {
        logger.info("delete post controller...!");
        movieService.deleteMovie(id);
        return HttpStatus.ACCEPTED;
    }
}
