package com.lucatic.grupo2.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase modelo DTO EventResponse para las respuestas en relacion con eventos.
 * Posee los getters, setters, toString y constuctores generados por
 * Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse implements Serializable {

	/**
	 * Atributo de serialVersionUID para Serializable.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo Long id de los eventos.
	 */
	private Long id;

	/**
	 * Atributo string nombre del evento
	 */
	private String name;

	/**
	 * Atributo string descripcion corta del evento
	 */
	private String shortDescription;

	/**
	 * Atributo string descripcion larga del evento
	 */
	private String longDescription;

	/**
	 * Atributo string con la direccion url de la imagen del evento
	 */
	private String photo;

	/**
	 * Atributo String con las normas del evento
	 */
	private String rules;

	/**
	 * Fecha de inicio
	 */
	private String initDate;

	/**
	 * Fecha de fin
	 */
	private String endDate;

	/**
	 * Hora de apertura
	 */
	private String timeOpen;

	/**
	 * Atributo List<RoomResponse> con la lista de respuestas de salas del evento
	 */
	private List<RoomResponse> roomResponses = new ArrayList<>();

	/**
	 * Metodo para agregar una nueva RoomResponse a la lista de RoomResponse
	 * 
	 * @param roomResponse que se quiere incorporar
	 */
	public void addRoomResponse(RoomResponse roomResponse) {
		roomResponses.add(roomResponse);
	}
}
