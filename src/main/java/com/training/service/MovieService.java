package com.training.service;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.training.exceptions.FileUploadException;
import com.training.exceptions.MovieResourceNotFoundException;
import com.training.models.Movie;
import com.training.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    MovieRepository repository;

    public void uploadAllMovie(MultipartFile file) {
        try {
            logger.info("uploadAllMovie {}", this.getClass().getName());
            uploadAllMovie(file.getInputStream());
        } catch (IOException e) {
            throw new FileUploadException("fail to store csv data: " + e.getMessage());
        }
    }

    public void uploadAllMovie(InputStream inputStream){
        List<Movie> movieList = CSVHelper.csvToTutorials(inputStream);
        repository.saveAll(movieList);
    }


    public List<Movie> getAllMovies() {
        logger.info("getAllMovie {}", this.getClass().getName());
        return repository.findAll();
    }

    public String update(String id, Movie movie) {
        logger.info("update movie {} ", this.getClass().getName());
        try {
            return repository.update(id, movie);
        } catch (ResourceNotFoundException ex) {
            logger.error("updateMovie {} Record not found", this.getClass().getName());
            throw new MovieResourceNotFoundException(id);
        }
    }

    public Movie findMovieById(String movieId) {
        logger.info("findMovieById {}", this.getClass().getName());
        try {
            return repository.findById(movieId);
        } catch (ResourceNotFoundException ex) {
            logger.error("findMovieById {} Record not found", this.getClass().getName());
            throw new MovieResourceNotFoundException(movieId);
        }

    }

    public Movie addNewMovie(Movie movie) {
        logger.info("addNewMovie {}", this.getClass().getName());
        repository.save(movie);
        return movie;
    }

    public void deleteMovie(String id) {
        logger.info("deleteMovie {}", this.getClass().getName());
        try {
            repository.delete(id);
        } catch (ResourceNotFoundException ex) {
            logger.error("deleteMovie {} Record not found", this.getClass().getName());
            throw new MovieResourceNotFoundException(id);
        }
    }
}
