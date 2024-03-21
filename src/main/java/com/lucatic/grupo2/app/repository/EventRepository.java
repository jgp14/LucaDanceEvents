package com.lucatic.grupo2.app.repository;

import com.lucatic.grupo2.app.model.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Genera una interfaz que hereda metodos de accion en la base de datos,
 * gestiona objetos tipo Event
 * 
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
public interface EventRepository extends JpaRepository<Event, Long> {

	/**
	 * Genera una lista de eventos por nombre del evento
	 * 
	 * @param nombre del parametro de la cosulta
	 * @return devuelve una lista de eventos con nombre concreto.
	 */
	List<Event> findEventByNameContainingIgnoreCase(String nombre);
}
