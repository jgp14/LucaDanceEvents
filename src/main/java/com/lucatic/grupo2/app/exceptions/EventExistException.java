package com.lucatic.grupo2.app.exceptions;

/**
 * Clase personalizada para manejo excepciones en los metodos del proyecto
 * Events, que hereda de Exception
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 06-03-2024
 */
public class EventExistException extends Exception {

	/**
	 * Constructor que recibe un mensaje
	 * 
	 * @param message el mensaje de error
	 */
	public EventExistException(String message) {
		super(message);
	}

	/**
	 * Constructor que recibe un mensaje y la causa lanzable
	 * 
	 * @param message el mensaje de error
	 * @param cause   el error
	 */
	public EventExistException(String message, Throwable cause) {
		super(message, cause);
	}
}