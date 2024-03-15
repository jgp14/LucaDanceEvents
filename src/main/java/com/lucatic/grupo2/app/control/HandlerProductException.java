package com.lucatic.grupo2.app.control;

import com.lucatic.grupo2.app.exceptions.EmptyListException;
import com.lucatic.grupo2.app.exceptions.EventExistException;
import com.lucatic.grupo2.app.model.Error;
import com.lucatic.grupo2.app.model.dto.EventResponseWithError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

/**
 * Clase Handler que realiza el manejo personalizado de errores
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
@RestControllerAdvice
public class HandlerProductException {


	/**
	 * Error método no soportado
	 *
	 * @param e La excepción
	 * @return la respuesta
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<EventResponseWithError> errorGenericoRuntime(RuntimeException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error procesando petición");
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<EventResponseWithError> errorGenericoRuntime(Exception e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error genérico procesando petición");
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

    @ExceptionHandler(EventExistException.class)
    public ResponseEntity<EventResponseWithError> errorEventExist(EventExistException e) {
        Error error = new Error();
        error.setDate(LocalDateTime.now());
        error.setError("Error ya existe el evento");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        EventResponseWithError eventResponseWithError = new EventResponseWithError();
        eventResponseWithError.setError(error);
        eventResponseWithError.setErrorBool(true);
        // return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
    }


    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<EventResponseWithError> errorEventExist(EmptyListException e) {
        Error error = new Error();
        error.setDate(LocalDateTime.now());
        error.setError("Error la lista está vacía");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        EventResponseWithError eventResponseWithError = new EventResponseWithError();
        eventResponseWithError.setError(error);
        eventResponseWithError.setErrorBool(true);
        // return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<EventResponseWithError> errorNoHandlerFound(NoHandlerFoundException e) {
        Error error = new Error();
        error.setDate(LocalDateTime.now());
        error.setError("Error en la URL");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        EventResponseWithError eventResponseWithError = new EventResponseWithError();
        eventResponseWithError.setError(error);
        eventResponseWithError.setErrorBool(true);
        // return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
    }

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<EventResponseWithError> errorNoHandlerFound(HttpRequestMethodNotSupportedException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la URL");
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}
}
