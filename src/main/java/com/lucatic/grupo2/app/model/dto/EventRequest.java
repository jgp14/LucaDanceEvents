package com.lucatic.grupo2.app.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


	/**
	 * Atributo string nombre del evento
	 */
	@NotBlank
	private String name;
	
	/**
	 * Atributo string descripcion corta del evento
	 */
	@NotBlank
	private String shortDescription;
	
	/**
	 * Atributo string descripcion larga del evento
	 */
	@NotBlank
	private String longDescription;
	
	/**
	 * Atributo string con la direccion url de la imagen del evento
	 */
	@NotBlank
	private String photo;
	
    /**
     * Atributo string con la fecha inicial parseada del evento en la sala
     */
	@NotBlank
	private String initDate;
	
    /**
     * Atributo string con la fecha final parseada del evento en la sala
     */
	@NotBlank
	private String endDate;
	
    /**
     * Atributo string el tiempo de duracion del evento. 
     */
	@NotBlank
	private String timeOpen;
	
    /**
     * Atributo string con el precio del evento. 
     */
	@NotBlank
	private String price;
	
	/**
	 * Atributo String con las normas del evento
	 */
	@NotBlank
	private String rules;

	/**
	 * Atributo con la lista de RoomRequest
	 */
	@Valid
	private List<RoomRequest> roomRequests = new ArrayList<>();
}
