package com.lucatic.grupo2.app.repository;

import com.lucatic.grupo2.app.model.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	/**
	 * Genera una lista de eventos por tipo o genero del evento
	 * 
	 * @param roomType nombre del genero de la sala
	 * @return List<Event> lista de eventos con el mismo tipo de genero.
	 */
	@Query("SELECT e FROM Event e JOIN e.rooms r WHERE r.roomType LIKE %:roomType%")
	List<Event> findEventsByRoomType(@Param("roomType") String roomType);
}
