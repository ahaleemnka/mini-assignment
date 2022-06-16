package com.training.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heart-beat")
public class HeartBeatController {

    Logger logger = LoggerFactory.getLogger(HeartBeatController.class);

    @GetMapping
    public ResponseEntity<Object> checkHeartBeat(){
        logger.info("heart beat checking");
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
