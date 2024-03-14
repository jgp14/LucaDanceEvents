package com.lucatic.grupo2.app.service;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.dto.EventRequest;

import java.util.List;

public interface EventService {

    List<Event> findAll() throws EmptyListException;
    Event findById(Long id);
    Event update(Event event);
    void deleteById(Long id);
    Event save(EventRequest event);
}
