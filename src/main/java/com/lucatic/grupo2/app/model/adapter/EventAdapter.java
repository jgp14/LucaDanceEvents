package com.lucatic.grupo2.app.model.adapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.lucatic.grupo2.app.model.EnumPriceRange;
import com.lucatic.grupo2.app.model.EventRoom;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.model.dto.EventExistResponseWithError;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.EventResponse;
import com.lucatic.grupo2.app.model.dto.EventResponseWithError;

import com.lucatic.grupo2.app.model.dto.RoomResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lucatic.grupo2.app.model.Event;

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

		eventResponse.setInitDate(event.getInitDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		eventResponse.setEndDate(event.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		eventResponse.setTimeOpen(event.getTimeOpen().format(DateTimeFormatter.ofPattern("HH:mm")));
		eventResponse.setRules(event.getRules());
		eventResponse.setPrice(event.getPrice().toString());

		List<RoomResponse> roomResponses = new ArrayList<>();
		for (EventRoom er : event.getEventRooms()) {
			roomResponses.add(new RoomResponse(er.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
					er.getInitTime().format(DateTimeFormatter.ofPattern("HH:mm")),
					er.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")), er.getRoom().getName(),
					er.getRoom().getCity(), er.getRoom().getAddress(), er.getRoom().getRoomType(),
					er.getRoom().getCapacity()));
		}
		eventResponse.setRoomResponses(roomResponses);

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
		for (int i = 0; i < eventRequest.getRoomRequests().size(); ++i) {
			eventRooms.add(new EventRoom(event, rooms.get(i),
					LocalDate.parse(eventRequest.getRoomRequests().get(i).getDate(),
							DateTimeFormatter.ofPattern("dd-MM-yyyy")),
					LocalTime.parse(eventRequest.getRoomRequests().get(i).getInitTime(),
							DateTimeFormatter.ofPattern("HH:mm")),
					LocalTime.parse(eventRequest.getRoomRequests().get(i).getEndTime(),
							DateTimeFormatter.ofPattern("HH:mm"))));
		}

		event.setEventRooms(eventRooms);

		return event;
	}

	/**
	 * Convierte un booleano que indica si un evento existe en una respuesta de
	 * evento de salida con error.
	 *
	 * @param eventExist Booleano que indica si el evento existe.
	 * @return EventExistResponseWithError La respuesta que indica si el evento
	 *         existe y posiblemente errores.
	 */
	public EventExistResponseWithError toExitEventResponseWithError(boolean eventExist) {
		LOGGER.info("EventExistResponseWithError: " + eventExist);
		return new EventExistResponseWithError(null, eventExist, false);
	}

	public EventResponse toEventResponse(Event event) {
		List<RoomResponse> roomResponses = new ArrayList<>();
		for (EventRoom er : event.getEventRooms()) {
			roomResponses.add(new RoomResponse(er.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
					er.getInitTime().format(DateTimeFormatter.ofPattern("HH:mm")),
					er.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")), er.getRoom().getName(),
					er.getRoom().getCity(), er.getRoom().getAddress(), er.getRoom().getRoomType(),
					er.getRoom().getCapacity()));
		}
		return new EventResponse(event.getId(), 
				event.getName(), 
				event.getShortDescription(), 
				event.getLongDescription(), 
				event.getPhoto(),
				event.getRules(),
				event.getPrice().toString(),
				event.getInitDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				,event.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				event.getTimeOpen().format(DateTimeFormatter.ofPattern("HH:mm")),
				roomResponses);
	}
}
