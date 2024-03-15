package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO EventResponseWithError para las respuestas Posee los
 * getters, setters, toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseWithError {

	/**
	 * Atrubuto Clase eventResponse
	 */
	private EventResponse eventResponse;

	/**
	 * Atrubuto clase Error
	 */
	private Error error;

	/**
	 * Atributo boolean si hay error
	 */
	private boolean isErrorBool;
}
