package com.lucatic.grupo2.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucatic.grupo2.app.model.EnumPriceRange;
import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.Room;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private String photo;
	private LocalDate date;
	private LocalTime time;
	private String rules;
	private String room;
	private String roomAddress;
	private Long idRoom;
	private boolean isError;

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

		if (event.getRooms() != null && !event.getRooms().isEmpty()) {
			
			//TODO
//			Room room = event.getRooms().get(0); 			
//			eventResponse.setRoom(room.getName());
//			eventResponse.setRoomAddress(room.getAddress());
//			eventResponse.setIdRoom(room.getId());
		}
		eventResponse.setError(false);
		return eventResponse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRoomAddress() {
		return roomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public static List<EventResponse> of(List<Event> events) {
		log.info("----- Evemtps:" + events);
		return events.stream().map(p -> of(p)).collect(Collectors.toList());
	}

}
