package com.lucatic.grupo2.app.exceptions;

/**
 * Clase personalizada de manejo de excepciones de lista vacias
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
public class EmptyListException extends EventException {

	/**
	 * Constructor que recibe un mensaje
	 * 
	 * @param message
	 */
	public EmptyListException(String message) {
		super(message);
	}

	/**
	 * Constructor que recibe un mensaje y la causa lanzable
	 * 
	 * @param message
	 * @param cause
	 */
	public EmptyListException(String message, Throwable cause) {
		super(message, cause);
	}
}
