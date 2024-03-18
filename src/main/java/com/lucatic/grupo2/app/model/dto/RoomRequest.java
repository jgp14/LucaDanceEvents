package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Room;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


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
@Valid
public class RoomRequest {

	/** NOmbre de la sala */
	@NotBlank
	private String name;
	/** Ciudad de la sala */
	@NotBlank
	private String city;
	/** Dirección */
	@NotBlank
	private String address;
	/** Tipo salsa - bachata - kizomba */
	@NotBlank
	private String roomType;
	/** Capacidad de la salsa */
	@NotNull
	@Positive
	private Integer capacity;


	/**
	 * Atributo string con la fecha de la sala parseada.
	 */
	@NotEmpty
	private String date;

	/**
	 * Atributo string con la fecha inicial parseada del evento en la sala
	 */
	@NotEmpty
	private String initTime;

	/**
	 * Atributo string con la fecha final parseada del evento en la sala
	 */
	@NotEmpty
	private String endTime;


}
