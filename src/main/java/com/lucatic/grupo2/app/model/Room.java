package com.lucatic.grupo2.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
