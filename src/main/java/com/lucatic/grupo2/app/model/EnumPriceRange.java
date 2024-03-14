package com.lucatic.grupo2.app.model;

public enum EnumPriceRange {
    BASICPASS(30),
    FULLPASS(50),
    VIPPASS(85);

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
