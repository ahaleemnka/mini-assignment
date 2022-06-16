package com.training;

import com.amazonaws.util.StringUtils;
import com.training.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class UploadRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UploadRunner.class);
    @Autowired
    private MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        if(args != null && args.length >0) {
            logger.info("Uploading command line runner file : {}", args[0]);
            movieService.uploadAllMovie(Files.newInputStream(Paths.get(args[0])));
        }
    }
}
