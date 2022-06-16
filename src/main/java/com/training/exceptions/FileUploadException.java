package com.training.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileUploadException extends RuntimeException{

    public FileUploadException(String message) {
        super(message);
    }
}
