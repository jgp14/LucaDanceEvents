package com.lucatic.grupo2.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Room> rooms;

    private String name;
    private String shortDescription;
    private String longDescription;
    private String photo;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    private EnumPriceRange price;
    private String rules;

}
