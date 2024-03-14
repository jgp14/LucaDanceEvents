package com.lucatic.grupo2.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucatic.grupo2.app.model.EnumPriceRange;
import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.repository.EventRepository;

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
	private List<Room> roomList = new ArrayList<>();
	private boolean isError;

	public void addRoom(Room room) {
		roomList.add(room);
	}
}
