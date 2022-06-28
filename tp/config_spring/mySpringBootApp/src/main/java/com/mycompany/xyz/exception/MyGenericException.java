package com.mycompany.xyz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MyGenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyGenericException() {
	}

	public MyGenericException(String message) {
		super(message);
	}

	public MyGenericException(Throwable cause) {
		super(cause);
	}

	public MyGenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyGenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
