package com.lucatic.grupo2.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;


@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    private EventRoom eventRoom;

    public String name;
    private String city;
    private String address;
    private String roomType;
    private Integer capacity;

    public Room() {}

    public Room(EventRoom eventRoom, String name, String city, String address, String roomType, Integer capacity) {
        this.eventRoom = eventRoom;
        this.name = name;
        this.city = city;
        this.address = address;
        this.roomType = roomType;
        this.capacity = capacity;
    }

    public Room(String name, String city, String address, String roomType, Integer capacity) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.roomType = roomType;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventRoom getEventRoom() {
        return eventRoom;
    }

    public void setEventRoom(EventRoom eventRoom) {
        this.eventRoom = eventRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
