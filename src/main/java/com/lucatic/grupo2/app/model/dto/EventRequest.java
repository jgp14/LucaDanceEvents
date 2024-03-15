package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String photo;
    private String price;
    private String rules;
    private String roomName;

    private String initDate;
    private String endDate;
    private String timeOpen;

    private String date;
    private String initTime;
    private String endTime;

    private List<Room> rooms;
//
//    private String roomAddress;
//    private String roomCity;
//    private Integer roomCapacity;
//    private String roomType;
}
