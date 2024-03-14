package com.lucatic.grupo2.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public String name;
    private String city;
    private String address;
    private String roomType;
    private Integer capacity;

    public Room(String name, String city, String address, String roomType, Integer capacity) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.roomType = roomType;
        this.capacity = capacity;
    }
}
