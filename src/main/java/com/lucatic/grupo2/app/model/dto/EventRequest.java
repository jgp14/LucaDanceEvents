package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.EnumPriceRange;
import com.lucatic.grupo2.app.model.Room;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase modelo DTO EventRequest para peticiones Posee los getters, setters,
 * toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private String photo;
	private String initDate;
	private String endDate;
	private String timeOpen;
	private String price;
	private String rules;

	private List<RoomRequest> roomRequests = new ArrayList<>();
}
