package com.lucatic.grupo2.app.service;

import com.lucatic.grupo2.app.exceptions.EmptyListException;
import com.lucatic.grupo2.app.exceptions.EventException;
import com.lucatic.grupo2.app.exceptions.EventExistException;
import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.dto.EventRequest;

import java.util.List;

/**
 * Interfaz de servicio
 * 
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
public interface EventService {
	/**
	 * Lista todos los eventos almacenados
	 * 
	 * @return devuelve una lista de objetos Event
	 * @throws EmptyListException gestiona este tipo de excepcion
	 */
	List<Event> findAll() throws EmptyListException;

	/**
	 * Selecciona un evento segun el id de este
	 * 
	 * @param id parametro con id de un Event
	 * @return devuelve un objeto tipo Event
	 */
	Event findById(Long id) throws EventException;

	/**
	 * Actualiza un evento y lo devuelve
	 * 
	 * @param event recibe un evento de parametro a actualizar
	 * @return comprueba que el evento este actualizado
	 */
	Event update(EventRequest eventRequest, Long id) throws EventException;

	/**
	 * Elimina un registro de la base de datos con un objeto que tiene el id de
	 * parametro
	 * 
	 * @param id parametro de un registro a borrar
	 */
	Event deleteById(Long id) throws EventException;

	/**
	 * Guarda un objeto de tipo EventRequest
	 * 
	 * @param event recupera un objeto de tipo EventRequest
	 * @return devuelve un objeto tipo Event
	 * @throws EventExistException Gestiona este tipo de excepcion
	 */
	Event save(EventRequest event) throws EventException;

	/**
	 * Comprueba si un objeto de tipo Event ya existe
	 * 
	 * @param id identificador del evento
	 * @return boolean si se encontro o no el evento.
	 */
	boolean eventFindById(Long id);

	/**
	 * Devuelve lista de eventos buscados por nombre
	 * 
	 * @param name de los eventos a buscar
	 * @return List<Event> listado de eventos con nombre igual
	 * @throws EmptyListException cuando la lista de eventos esta vacia.
	 */
	List<Event> findByName(String name) throws EmptyListException;

	/**
	 * Devuelve lista de eventos buscados por genero de evento
	 * 
	 * @param roomType nombre o tipo genero de los eventos a buscar
	 * @return List<Event> listado de eventos con el mismo genero.
	 * @throws EmptyListException cuando la lista de eventos esta vacia.
	 */
	List<Event> findEventsByRoomType(String roomType) throws EmptyListException;

}
