package com.lucatic.grupo2.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRoom> eventRooms = new ArrayList<>();

    private String name;
    private String shortDescription;
    private String longDescription;
    private String photo;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    private EnumPriceRange price;
    private String rules;

    public void addEventRoom(EventRoom eventRoom) {
        eventRooms.add(eventRoom);
    }

}
