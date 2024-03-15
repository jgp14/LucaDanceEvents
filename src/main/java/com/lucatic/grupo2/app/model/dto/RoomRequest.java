package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase modelo DTO RoomRequest para peticiones en relacion con salas. Posee los
 * getters, setters, toString y constuctores generados por Lombok @Data.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

	/**
	 * Atrubuto Clase Room que ya incluye los atributos de la sala
	 */
	private Room room;

	/**
	 * Atributo string con la fecha de la sala parseada.
	 */
	private String date;

	/**
	 * Atributo string con la fecha inicial parseada del evento en la sala
	 */
	private String initTime;

	/**
	 * Atributo string con la fecha final parseada del evento en la sala
	 */
	private String endTime;
}
