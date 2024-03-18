package com.lucatic.grupo2.app.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private String date;
    private String initTime;
    private String endTime;
    public String name;
    private String city;
    private String address;
    private String roomType;
    private Integer capacity;
}
