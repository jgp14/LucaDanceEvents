package com.lucatic.grupo2.app.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    public String name;
    private String city;
    private String address;
    private String roomType;
    private Integer capacity;

    private String roomDate;
    private String roomInitTime;
    private String roomEndTime;

}
