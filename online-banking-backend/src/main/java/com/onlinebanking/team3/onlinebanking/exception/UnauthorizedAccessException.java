package com.onlinebanking.team3.onlinebanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends Exception {
	private static final long serialVersionUID = 1L;
	public UnauthorizedAccessException(String message) {super(message);}
}
