package com.lucatic.grupo2.app.model;

/**
 * Clase enum que modela el rango de precio de los eventos.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 06-03-2024
 */
public enum EnumPriceRange {
	BASICPASS(30), FULLPASS(50), VIPPASS(85);

	private Integer value;

	EnumPriceRange(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
