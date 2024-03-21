package com.lucatic.grupo2.app.service;

import com.lucatic.grupo2.app.exceptions.EmptyListException;
import com.lucatic.grupo2.app.exceptions.EventException;
import com.lucatic.grupo2.app.exceptions.EventExistException;
import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.EventRoom;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.model.adapter.EventAdapter;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.RoomRequest;
import com.lucatic.grupo2.app.repository.EventRepository;
import com.lucatic.grupo2.app.repository.EventRoomRepository;
import com.lucatic.grupo2.app.repository.RoomRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa el servicio de Eventos
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {
	/**
	 * Auto instancia el objeto de repositorio para eventos
	 */
	@Autowired
	private EventRepository eventRepository;
	/**
	 * Auto instancia el objeto de repositorio para salas
	 */
	@Autowired
	private RoomRepository roomRepository;
	/**
	 * Auto instancia el objeto de repositorio para la relacion entre eventos y
	 * salas
	 */
	@Autowired
	private EventRoomRepository eventRoomRepository;
	/**
	 * Auto instancia el objeto adaptador, realiza el trato de DTOs
	 */
	@Autowired
	private EventAdapter eventAdapter;

	/**
	 * Lista todos los eventos existentes
	 * 
	 * @return devuelve una lista de eventos
	 * @throws EmptyListException propaga la excepcion en caso de no haber eventos
	 */
	@Override
	public List<Event> findAll() throws EmptyListException {
		List<Event> events = eventRepository.findAll();
		if (events.isEmpty())
			throw new EmptyListException("Lista de eventos vacía");
		else
			return events;
	}

	/**
	 * Devuelve un objeto tipo Event filtrado por un id concreto
	 * 
	 * @param id tiene el id de un objeto Event
	 * @return devuelve un objeto tipo Event
	 */
	@Override
	public Event findById(Long id) throws EventException {
		return eventRepository.findById(id).orElseThrow(() -> new EventExistException("El evento no existe"));
	}

	/**
	 * Actualiza un evento concreto
	 *
	 * @param event recibe un objeto Event preparado para actualzar
	 * @return devuelve un Event actualizado
	 * @throws EventException cuadno se produce una exepcion.
	 */
	@Override
	public Event update(EventRequest eventRequest, Long id) throws EventException {

		if (eventRequest == null)
			throw new EventException("Event request is null");

		Event eventExisting = findById(id);

		if (eventExisting == null) {
			throw new EventExistException("Event not found in eventdb");
		}
		List<Room> rooms = new ArrayList<>();

		for (RoomRequest roomRequest : eventRequest.getRoomRequests()) {
			Room roomFound = roomRepository.findRoomByNameAndAddress(roomRequest.getName(), roomRequest.getAddress());
			if (roomFound == null) {
				roomFound = new Room(roomRequest.getName(), roomRequest.getCity(), roomRequest.getAddress(),
						roomRequest.getRoomType(), roomRequest.getCapacity());
				roomRepository.save(roomFound);
			}
			rooms.add(roomFound);
		}

		Event event = eventAdapter.fromEventRequest(eventRequest, rooms);
		for (EventRoom eventRoomAux : event.getEventRooms()) {
			eventRoomAux.setEvent(event);
		}
		event.setId(id);
		event = eventRepository.saveAndFlush(event);
		return event;
	}

	/**
	 * Elimina un objeto con un id determinado
	 *
	 * @param id recibe un id de un objeto a borrar
	 */
	@Override
	public Event deleteById(Long id) throws EventException {
		if (id == null)
			throw new EventException("IlegalArgumentException: id de evento a borrar es nulo. ");
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new EventException("Evento no éxiste para borrar"));

		eventRepository.deleteById(id);
		return event;
	}

	/**
	 * Metodo que guarda un evento concreto
	 * 
	 * @param eventRequest se encarga de coger datos tratables a guarda
	 * @throws EventException Si intenta dar de alta un evento nulo
	 * @return devuelve un objeto tipo Event tratado
	 */
	@Override
	public Event save(@Valid EventRequest eventRequest) throws EventException {

		if (eventRequest == null)
			throw new EventException("Event request is null");

		List<Room> rooms = new ArrayList<>();

		for (RoomRequest roomRequest : eventRequest.getRoomRequests()) {
			Room roomFound = roomRepository.findRoomByNameAndAddress(roomRequest.getName(), roomRequest.getAddress());
			if (roomFound == null) {
				roomFound = new Room(roomRequest.getName(), roomRequest.getCity(), roomRequest.getAddress(),
						roomRequest.getRoomType(), roomRequest.getCapacity());
				roomRepository.save(roomFound);
			}
			rooms.add(roomFound);
		}

		Event event = eventAdapter.fromEventRequest(eventRequest, rooms);

		for (EventRoom eventRoomAux : event.getEventRooms()) {
			eventRoomAux.setEvent(event);
		}
		event = eventRepository.save(event);

		return event;
	}

	@Override
	public boolean eventFindById(long id) {
		if (eventRepository.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public List<Event> findByName(String name) throws EmptyListException {
		List<Event> events = eventRepository.findEventByNameContainingIgnoreCase(name);
		if (events.isEmpty())
			throw new EmptyListException("Lista de eventos vacía");
		else
			return events;
	}
}
