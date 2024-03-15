package com.lucatic.grupo2.app.exceptions;

/**
 * Clase personalizada para manejo excepciones de si existe evento, que hereda
 * de EventException
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
public class EventExistException extends EventException {

	/**
	 * Genera un serial para la excepcion personalizada
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje del fallo
	 * 
	 * @param message el mensaje de error del evento ya exitente
	 */
	public EventExistException(String message) {
		super(message);
	}

	/**
	 * Constructor que recibe un mensaje y la causa lanzable
	 * 
	 * @param message el mensaje de error del evento ya exitente
	 * @param cause   Detalles de motivos de la causa throwable
	 */
	public EventExistException(String message, Throwable cause) {
		super(message, cause);
	}
}