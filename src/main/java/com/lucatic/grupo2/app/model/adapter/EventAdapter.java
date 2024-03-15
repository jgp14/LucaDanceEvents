package com.lucatic.grupo2.app.model.adapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lucatic.grupo2.app.model.EnumPriceRange;
import com.lucatic.grupo2.app.model.EventRoom;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.EventResponseWithError;
import com.lucatic.grupo2.app.model.dto.RoomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.dto.EventResponse;

/**
 * Clase adapter que convierte entidades a dtos. La entidad Event a
 * EventResponseWithError y EventRequest a Event.
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
@Component
public class EventAdapter {

	/**
	 * Logger que registra los errores de la clase EventAdapter.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(EventResponse.class);

	/**
	 * Metodo que transforma de entidad evento a dto respuesta de evento.
	 *
	 * @param event Entidad que se le pasa para ser transformada.
	 * @return EventResponseWithError La respuesta con el evento transformado y
	 *         error incluido que devuelve.
	 */
	public EventResponseWithError toEventResponseWithError(Event event) {
		EventResponse eventResponse = new EventResponse();
		eventResponse.setId(event.getId());
		eventResponse.setName(event.getName());
		eventResponse.setShortDescription(event.getShortDescription());
		eventResponse.setLongDescription(event.getLongDescription());
		eventResponse.setPhoto(event.getPhoto());

		eventResponse.setInitDate(event.getInitDate());
		eventResponse.setEndDate(event.getEndDate());
		eventResponse.setTimeOpen(event.getTimeOpen());
		eventResponse.setRules(event.getRules());

		for (EventRoom er: event.getEventRooms()) {
			RoomResponse roomResponse = new RoomResponse();
			roomResponse.setCity(er.getRoom().getCity());
			roomResponse.setAddress(er.getRoom().getAddress());
			roomResponse.setRoomType(er.getRoom().getRoomType());
			roomResponse.setCapacity(er.getRoom().getCapacity());
			roomResponse.setId(er.getRoom().getId());
			roomResponse.setName(er.getRoom().getName());
			roomResponse.setRoomDate(er.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			roomResponse.setRoomInitTime(er.getInitTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			roomResponse.setRoomEndTime(er.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			eventResponse.addRoomResponse(roomResponse);

		}
		EventResponseWithError eventResponseWithError = new EventResponseWithError();
		eventResponseWithError.setEventResponse(eventResponse);
		eventResponseWithError.setErrorBool(false);
		return eventResponseWithError;
	}

	/**
	 * Metodo que transforma el dto request del evento en una entidad Event.
	 *
	 * @param eventRequest se le pasa para ser transformado.
	 * @return Event La entidad del modelo
	 */
	public Event fromEventRequest(EventRequest eventRequest, List<Room> rooms) {


		Event event = new Event();
		event.setId(eventRequest.getId());
		event.setName(eventRequest.getName());
		event.setShortDescription(eventRequest.getShortDescription());
		event.setLongDescription(eventRequest.getLongDescription());
		event.setPhoto(eventRequest.getPhoto());
		event.setInitDate(LocalDate.parse(eventRequest.getInitDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		event.setEndDate(LocalDate.parse(eventRequest.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		event.setTimeOpen(LocalTime.parse(eventRequest.getTimeOpen(), DateTimeFormatter.ofPattern("HH:mm")));
		event.setPrice(EnumPriceRange.valueOf(eventRequest.getPrice()));
		event.setRules(eventRequest.getRules());

		List<EventRoom> eventRooms = new ArrayList<>();
		for (Room roomFor: rooms) {
			EventRoom eventRoomResult = new EventRoom(event, roomFor);
			eventRoomResult.setDate(LocalDate.parse(eventRequest.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			eventRoomResult.setInitTime(LocalTime.parse(eventRequest.getInitTime(), DateTimeFormatter.ofPattern("HH:mm")));
			eventRoomResult.setEndTime(LocalTime.parse(eventRequest.getEndTime(), DateTimeFormatter.ofPattern("HH:mm")));

//			roomFor.setEventRoom(eventRoomResult);
			eventRooms.add(eventRoomResult);
		}

		event.setEventRooms(eventRooms);
		/*event.setRooms(rooms);
		for (Room r: rooms) {
			r.setEvent(event);
		}*/
		return event;
	}
	/*
	public  List<EventResponse> toEventResponse(List<Event> events) {
		LOGGER.info("----- Evemtps:" + events);
		return events.stream().map(p -> toEventResponse(p)).collect(Collectors.toList());
	}*/
}
