package com.lucatic.grupo2.app.control;

import java.net.URI;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.service.EventService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucatic.grupo2.app.exceptions.EventExistException;

@RestController
@RequestMapping("/event")
class EventController {

	private final static Logger LOGGER = LogManager.getLogger(EventController.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private EventAdapter eventAdapter;

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody EventRequest eventRequest) throws EventExistException{

		try {
			Event event= eventService.save(eventRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
			LOGGER.info("Event " + event.getName() + " with id " + event.getId() + " has been created");
			return ResponseEntity.created(location).build();

		} catch (EventExistException e) {
            		LOGGER.warn("Error dando de alta el juego " + e.getMessage());
            		throw e;
        	}
	}
}