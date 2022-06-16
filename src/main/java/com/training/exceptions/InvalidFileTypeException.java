package com.training.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException(String message) {
        super(message);
    }
}
