package com.lucatic.grupo2.app.model.adapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.lucatic.grupo2.app.model.EnumPriceRange;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.dto.EventResponse;

@Component
public class EventAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(EventResponse.class);
	
	public  EventResponse toEventResponse(Event event) {
		EventResponse eventResponse = new EventResponse();
		eventResponse.setId(event.getId());
		eventResponse.setName(event.getName());
		eventResponse.setShortDescription(event.getShortDescription());
		eventResponse.setLongDescription(event.getLongDescription());
		eventResponse.setPhoto(event.getPhoto());
		eventResponse.setDate(event.getDate());
		eventResponse.setTime(event.getTime());
		eventResponse.setRules(event.getRules());
		eventResponse.setRoomList(event.getRooms());
		eventResponse.setError(false);
		return eventResponse;
	}

	public Event fromEventRequest(EventRequest eventRequest, List<Room> rooms) {

		Event event = new Event();
		event.setId(eventRequest.getId());
		event.setName(eventRequest.getName());
		event.setShortDescription(eventRequest.getShortDescription());
		event.setLongDescription(eventRequest.getLongDescription());
		event.setPhoto(eventRequest.getPhoto());
		event.setDate(LocalDate.parse(eventRequest.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		event.setTime(LocalTime.parse(eventRequest.getTime(), DateTimeFormatter.ofPattern("HH:mm")));
		event.setPrice(EnumPriceRange.valueOf(eventRequest.getPrice()));
		event.setRules(eventRequest.getRules());

		event.setRooms(rooms);
		for (Room r: rooms) {
			r.setEvent(event);
		}
		return event;
	}
	/*
	public  List<EventResponse> toEventResponse(List<Event> events) {
		LOGGER.info("----- Evemtps:" + events);
		return events.stream().map(p -> toEventResponse(p)).collect(Collectors.toList());
	}*/
}