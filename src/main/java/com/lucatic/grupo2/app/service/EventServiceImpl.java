package com.lucatic.grupo2.app.service;

import com.lucatic.grupo2.app.exceptions.EmptyListException;
import com.lucatic.grupo2.app.exceptions.EventExistException;
import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.EventRoom;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.model.adapter.EventAdapter;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.repository.EventRepository;
import com.lucatic.grupo2.app.repository.EventRoomRepository;
import com.lucatic.grupo2.app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EventRoomRepository eventRoomRepository;

    @Autowired
    private EventAdapter eventAdapter;

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
    public Event save(EventRequest eventRequest) throws EventExistException {

        if (eventRepository.existsById(eventRequest.getId())) {
            throw new EventExistException("No se puede dar de alta porque ya existe el evento");
        }

        List<Room> rooms = new ArrayList<>();

        for (Room r: eventRequest.getRooms()) {
            Room roomFound = roomRepository.findRoomByNameAndAddress(r.getName(), r.getAddress());
            if (roomFound == null) {
                roomFound = new Room(r.getName(), r.getCity(), r.getAddress(), r.getRoomType(), r.getCapacity());
                roomRepository.save(roomFound);
            }
            rooms.add(roomFound);
        }




        Event event = eventAdapter.fromEventRequest(eventRequest, rooms);

        for (EventRoom eventRoomAux: event.getEventRooms()) {
            eventRoomAux.setEvent(event);
        }
        eventRepository.save(event);
/*
        Event createEvent = new Event(
                eventRequest.getName(),
                eventRequest.getShortDescription(),
                eventRequest.getLongDescription(),
                eventRequest.getPhoto(),
                LocalDate.parse(eventRequest.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                LocalTime.parse(eventRequest.getTime(), DateTimeFormatter.ofPattern("HH:mm")),
                EnumPriceRange.valueOf(eventRequest.getPrice()),
                eventRequest.getRules()
                );

        if (!eventRequest.getRoomName().isEmpty() && !eventRequest.getRoomAddress().isEmpty()) {
            Room room = roomRepository.findRoomByNameAndAddress(eventRequest.getRoomName(), eventRequest.getRoomAddress());

            if (room == null) {
                room = new Room(eventRequest.getRoomName(), eventRequest.getRoomCity(), eventRequest.getRoomAddress(), eventRequest.getRoomType(), eventRequest.getRoomCapacity());

            }
            room.setEvent(createEvent);
            createEvent.addRoom(room);
            roomRepository.save(room);
            eventRepository.save(createEvent);
        }

        return createEvent;

 */
        return event;
    }
}
