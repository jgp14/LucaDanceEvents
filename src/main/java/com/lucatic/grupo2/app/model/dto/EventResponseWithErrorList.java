package com.lucatic.grupo2.app.model.dto;

import java.util.List;

import com.lucatic.grupo2.app.model.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseWithErrorList {
	/**
	 * Atributo con objeto Error
	 */
	private Error error;
	/**
	 * Atributo con objeto UserResponse
	 */
	private List<EventResponse> eventResponse;
	/**
	 * Atributo con boolean para decidir si es o no un error
	 */
	private boolean isErrorBool;
}
