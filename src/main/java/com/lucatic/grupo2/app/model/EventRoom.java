package com.lucatic.grupo2.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    public EventRoom() {
    }

    public EventRoom(Event event, Room room) {
        this.event = event;
        this.room = room;
    }

    public EventRoom(Long id, Event event, Room room) {
        this.id = id;
        this.event = event;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
