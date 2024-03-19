package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO EventExistResponseWithError.Posee los getters, setters,
 * toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventExistResponseWithError {
	/**
	 * Atrubuto clase Error
	 */
	private Error error;

	/**
	 * Atrubuto eventExistBool
	 */
	private boolean eventExistBool;

	/**
	 * Atributo boolean si hay error
	 */
	private boolean isErrorBool;
}
