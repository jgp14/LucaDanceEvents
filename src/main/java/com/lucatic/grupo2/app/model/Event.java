package com.lucatic.grupo2.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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

    public Event() {
    }

    public Event(List<EventRoom> eventRooms, String name, String shortDescription, String longDescription, String photo, LocalDate date, LocalTime time, EnumPriceRange price, String rules) {
        this.eventRooms = eventRooms;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.photo = photo;
        this.date = date;
        this.time = time;
        this.price = price;
        this.rules = rules;
    }

    public Event(String name, String shortDescription, String longDescription, String photo, LocalDate date, LocalTime time, EnumPriceRange price, String rules) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.photo = photo;
        this.date = date;
        this.time = time;
        this.price = price;
        this.rules = rules;
    }

    public void addEventRoom(EventRoom eventRoom) {
        eventRooms.add(eventRoom);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EventRoom> getEventRooms() {
        return eventRooms;
    }

    public void setEventRooms(List<EventRoom> eventRooms) {
        this.eventRooms = eventRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public EnumPriceRange getPrice() {
        return price;
    }

    public void setPrice(EnumPriceRange price) {
        this.price = price;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
