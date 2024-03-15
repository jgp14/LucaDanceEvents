package com.lucatic.grupo2.app.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	private String name;
	private String shortDescription;
	private String longDescription;
	private String photo;
	private LocalDate initDate;
	private LocalDate endDate;
	private LocalTime timeOpen;
	@Enumerated(EnumType.STRING)
	private EnumPriceRange price;
	private String rules;

	public Event(Long id) {
		this.id = id;
	}

	public void addEventRoom(EventRoom eventRoom) {
		eventRooms.add(eventRoom);
	}

}
