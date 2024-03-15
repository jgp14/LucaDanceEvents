package com.lucatic.grupo2.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase modelo DTO EventResponse para las respuestas Posee los getters,
 * setters, toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse implements Serializable {

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


	private String initDate;
	private String endDate;
	private String timeOpen;
	
	/**
	 * Atributo List<RoomResponse> con la lista de salas del evento
	 */	
	private List<RoomResponse> roomList = new ArrayList<>();

	public void addRoomResponse(RoomResponse roomResponse) {
		roomList.add(roomResponse);
	}
}
