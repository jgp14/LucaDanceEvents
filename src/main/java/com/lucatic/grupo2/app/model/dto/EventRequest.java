package com.lucatic.grupo2.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String photo;
    private String date;
    private String time;
    private String price;
    private String rules;
    private String roomName;
    private String roomAddress;
    private String roomCity;
    private Integer roomCapacity;
    private String roomType;
}
