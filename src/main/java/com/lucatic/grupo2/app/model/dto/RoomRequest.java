package com.lucatic.grupo2.app.model.dto;

import com.lucatic.grupo2.app.model.Room;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {

    private Room room;
    private String date;
    private String initTime;
    private String endTime;
}
