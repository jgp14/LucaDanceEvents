package com.lucatic.grupo2.app.model;

/**
 * Clase enum que modela el rango de precio de los eventos.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
public enum EnumPriceRange {
	BASICPASS(30), FULLPASS(50), VIPPASS(85);

	/**
	 * Atributo con el valor integer del precio
	 */
	private Integer value;

	/**
	 * Constructor de EnumPriceRange
	 * 
	 * @param value
	 */
	private EnumPriceRange(Integer value) {
		this.value = value;
	}

	/**
	 * Getter obtener valor precio
	 * 
	 * @return valor con el precio.
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * INserta un valor nuevo de precio
	 * 
	 * @param value valor del precio
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
}
