package com.pet.petorderservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Found")
public class NoOrderFoundException extends RuntimeException {

	private static final long serialVersionUID = 5999695099656741154L;

	public NoOrderFoundException(String exception) {
		
	}

}
