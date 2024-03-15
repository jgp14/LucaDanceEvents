package com.lucatic.grupo2.app.exceptions;

/**
 * Clase personalizada de manejo de excepciones relacionados con los eventos
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
public class EventException extends Exception {

	/**
	 * Genera un serial para la excepcion personalizada
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje del fallo
	 * 
	 * @param message descriptivo con el error del evento.
	 */
	public EventException(String message) {
		super(message);
	}

	/**
	 * Constructor que recibe un mensaje y la causa lanzable del fallo
	 * 
	 * @param message descriptivo con el error del evento.
	 * @param cause   Detalles de motivos de la causa throwable.
	 */
	public EventException(String message, Throwable cause) {
		super(message, cause);
	}
}
