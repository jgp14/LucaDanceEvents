package com.lucatic.grupo2.app.exceptions;
public class EventExistException extends Exception{

	public EventExistException (String message) {
		super(message);
	}

	public EventExistException (String message, Throwable cause) {
		super(message, cause);
	}
}