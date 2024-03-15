package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase modelo DTO EventRequest para peticiones Posee los getters, setters,
 * toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

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
	 * Atributo LocalDate con la fecha del evento
	 */
	private String date;

	/**
	 * Atributo LocalTime con la hora del evento
	 */
	private String time;

	/**
	 * Atributo String con el precio del evento
	 */
	private String price;

	/**
	 * Atributo String con las normas del evento
	 */
	private String rules;

	/**
	 * Atributo String con el nombre de la sala
	 */
	private String roomName;

	/**
	 * Atributo List<Room> con las salas del evento
	 */
	private List<Room> rooms;

	/**
	 * Atributo LocalDate con la fecha inicial del evento
	 */
    private String initTime;
    
	/**
	 * Atributo LocalDate con la fecha final del evento
	 */
    private String endTime;

//
//    private String roomAddress;
//    private String roomCity;
//    private Integer roomCapacity;
//    private String roomType;
}
