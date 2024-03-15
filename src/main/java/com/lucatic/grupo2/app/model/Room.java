package com.lucatic.grupo2.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase modelo Room de sala para la bbdd, requests y responses. Posee los
 * getters, setters y constuctores generados por Lombok.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

	/**
	 * Atributo Long id autoincremental de las salas.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Atributo string con el nombre de la sala.
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
	 * Constuctor personalidado 
	 * @param name de la sala
	 * @param city de la sala
	 * @param address de la sala
	 * @param roomType de la sala
	 * @param capacity de la sala
	 */
	public Room(String name, String city, String address, String roomType, Integer capacity) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.roomType = roomType;
		this.capacity = capacity;
	}
	
	
}
