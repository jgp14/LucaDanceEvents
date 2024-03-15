package com.lucatic.grupo2.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase modelo Entity de Evento para la bbdd, requests y responses. Posee los
 * gettes, settes y constuctores generados por Lombok
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

	/**
	 * Atributo Long id autoincremental de los eventos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Atributo lista de EventRoom, clase intermedia entre evento y sala. Esta
	 * mapeado y es cascade all.
	 */
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<EventRoom> eventRooms = new ArrayList<>();

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
	private LocalDate date;

	/**
	 * Atributo LocalTime con la hora del evento.
	 */
	private LocalTime time;

	/**
	 * Atributo del rango o precio del evento
	 */
	@Enumerated(EnumType.STRING)
	private EnumPriceRange price;

	/**
	 * Atributo string con las normas del evento
	 */
	private String rules;

	public void addEventRoom(EventRoom eventRoom) {
		eventRooms.add(eventRoom);
	}

}
