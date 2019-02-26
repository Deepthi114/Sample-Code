package com.pet.petorderservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation Failure")
public class ValidateOrderException extends RuntimeException {

	private static final long serialVersionUID = -3270619721743314201L;

	public ValidateOrderException(String exception) {

	}

}
