package com.lucatic.grupo2.app.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo DTO RoomResponse para las respuestas de Room Posee los getters,
 * setters, toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse implements Serializable {

	/**
	 * Atributo de serialVersionUID para Serializable.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo Long id de las salas.
	 */
	private Long id;

	/**
	 * Atributo String nombre de las salas
	 */
	public String name;

	/**
	 * Atributo string con el nombre de la ciudad de ubicacion de la sala.
	 */
	private String city;

	/**
	 * Atributo string con la direcion postal exacta de la sala.
	 */
	private String address;

	/**
	 * Atributo string con el tipo de sala que es.
	 */
	private String roomType;

	/**
	 * Atributo con el numero de afr¡oro maxima de la sala
	 */
	private Integer capacity;

	/**
	 * Atributo string con la fecha parseada del evento en la sala
	 */
	private String roomDate;

	/**
	 * Atributo string con la fecha inicial parseada del evento en la sala
	 */
	private String roomInitTime;

	/**
	 * Atributo string con la fecha final parseada del evento en la sala
	 */
	private String roomEndTime;

}
