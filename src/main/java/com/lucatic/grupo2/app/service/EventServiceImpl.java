package com.lucatic.grupo2.app.service;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.repository.EventRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() throws EmptyListException {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty())
            throw new EmptyListException("Lista de eventos vacía");
        else
            return events;
    }

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Event save(Event event) {
        return null;
    }
}
