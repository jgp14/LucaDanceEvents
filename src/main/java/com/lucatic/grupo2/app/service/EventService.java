package com.lucatic.grupo2.app.service;

import com.lucatic.grupo2.app.model.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();
    Event findById(Long id);
    Event update(Event event);
    void deleteById(Long id);
    Event save(Event event);
}