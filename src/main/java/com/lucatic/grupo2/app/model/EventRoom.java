package com.lucatic.grupo2.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_room")
public class EventRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Event event;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Room room;

    private LocalDate date;
    private LocalTime initTime;
    private LocalTime endTime;

    public EventRoom(Event event, Room room) {
        this.event = event;
        this.room = room;
    }

    public EventRoom(Event event) {
        this.event = event;
    }

    public EventRoom(LocalTime initTime, LocalTime endTime) {
        this.initTime = initTime;
        this.endTime = endTime;
    }
}
