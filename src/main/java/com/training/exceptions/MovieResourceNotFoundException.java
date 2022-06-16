package com.training.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieResourceNotFoundException extends RuntimeException {
	public MovieResourceNotFoundException(String msg) {
		super("No movie found with id : " + msg);
	}

}