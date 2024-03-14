package com.lucatic.grupo2.app.model.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.dto.EventResponse;

@Component
public class EventAdapter {

	private static Logger log = LoggerFactory.getLogger(EventResponse.class);
	
	public static EventResponse toEventResponse(Event event) {
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
	
	public static List<EventResponse> toEventResponse(List<Event> events) {
		log.info("----- Evemtps:" + events);
		return events.stream().map(p -> toEventResponse(p)).collect(Collectors.toList());
	}
}
