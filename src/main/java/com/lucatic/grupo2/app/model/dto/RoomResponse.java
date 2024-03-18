package com.lucatic.grupo2.app.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

	/**
	 * Atributo string fecha del evento
	 */
    private String date;
    
    /**
	 * Atributo string hora de inicio del evento
	 */
    private String initTime;
    
    /**
	 * Atributo string hora de fin del evento
	 */
    
    private String endTime;
    
    /**
	 * Atributo string nombre de la sala
	 */
    
    public String name;
    
    /**
	 * Atributo string ciudad de la sala
	 */
    private String city;
    
    /**
	 * Atributo string dirección de la sala
	 */
    private String address;
    
    /**
	 * Atributo string tipo de sala
	 */
    private String roomType;
    
    /**
	 * Atributo string capacidad de la sala
	 */
    private Integer capacity;
}
