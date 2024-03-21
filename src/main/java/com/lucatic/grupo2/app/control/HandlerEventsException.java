package com.lucatic.grupo2.app.control;

import com.lucatic.grupo2.app.exceptions.EmptyListException;
import com.lucatic.grupo2.app.exceptions.EventExistException;
import com.lucatic.grupo2.app.model.Error;
import com.lucatic.grupo2.app.model.dto.EventResponseWithError;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
public class HandlerEventsException {

	/**
	 * Logger que registra los errores de clase HandlerProductException
	 */
	private final static Logger LOGGER = LogManager.getLogger(HandlerEventsException.class);

	/**
	 * Error método error de ejecucion generico.
	 *
	 * @param e La excepción de ejecucion.
	 * @return ResponseEntity con la respuesta con su codigo de error.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<EventResponseWithError> errorGenericoRuntime(RuntimeException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error procesando petición");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		LOGGER.warn(error.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion generica
	 * 
	 * @param e La Excepcion
	 * @return ResponseEntity con la respuesta con su codigo de error.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<EventResponseWithError> errorGenericoRuntime(Exception e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error genérico procesando petición");
		error.setMessage(e.getClass().getCanonicalName());
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		LOGGER.warn(error.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}
	
	 /**
     * Maneja excepciones de argumentos de método no válidos.
     *
     * @param e La excepción de argumentos de método no válidos
     * @return ResponseEntity con la respuesta de error
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EventResponseWithError> errorGenericoRuntime(MethodArgumentNotValidException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en los datos del cliente, compruebelos");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName() + " " + e.getMessage().split(":")[1]);
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// return ResponseEntity.internalServerError().body(error);
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		LOGGER.warn(error.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion por Evento ya existente.
	 * 
	 * @param e La excepcion de evento no existe.
	 * @return ResponseEntity con la respuesta del error.
	 */
	@ExceptionHandler(EventExistException.class)
	public ResponseEntity<EventResponseWithError> errorEventExist(EventExistException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error ya existe el evento");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName() + " " + e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		LOGGER.warn(error.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion por lista vacia ya existente.
	 * 
	 * @param e La excepcion de lista vacia.
	 * @return ResponseEntity con la respuesta del error.
	 */
	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<EventResponseWithError> errorEventExist(EmptyListException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error la lista está vacía");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName() + " " + e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		LOGGER.warn(error.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion no se encontro el Handler.
	 * 
	 * @param e La excepcion de handler no encontrado.
	 * @return esponseEntity con la respuesta del error.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<EventResponseWithError> errorNoHandlerFound(NoHandlerFoundException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la URL");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		LOGGER.warn(error.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(eventResponseWithError);
	}

	/**
	 * Error de excepcion por Resquest Metodo no soportado.
	 * 
	 * @param e La excepcion de metodo no esta soportado.
	 * @return ResponseEntity con la respuesta del error.
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<EventResponseWithError> errorNoHandlerFound(HttpRequestMethodNotSupportedException e) {
		Error error = new Error();
		error.setDate(LocalDateTime.now());
		error.setError("Error en la URL");
		error.setMessage("Error del tipo " + e.getClass().getSimpleName());
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setError(error);
		eventResponseWithError.setErrorBool(true);
		// return ResponseEntity.internalServerError().body(error);
		LOGGER.warn(error.getMessage() + " " + e.getMessage());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(eventResponseWithError);
	}
}
