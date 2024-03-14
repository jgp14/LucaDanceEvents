package com.lucatic.grupo2.app.control;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.adapter.EventAdapter;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.EventResponse;

import com.lucatic.grupo2.app.exceptions.EmptyListException;

import com.lucatic.grupo2.app.service.EventService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<?> save(@Valid @RequestBody EventRequest eventRequest) throws EventExistException {

		try {
			Event event = eventService.save(eventRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId())
					.toUri();
			LOGGER.info("Event " + event.getName() + " with id " + event.getId() + " has been created");
			return ResponseEntity.created(location).build();

		} catch (EventExistException e) {
			LOGGER.warn("Error pushing the event" + e.getMessage());
			throw e;
		}

	}

	@GetMapping("/all")
	public ResponseEntity<?> listAll() throws EmptyListException {
		// return productAdapter.convertToDto(productService.getAll());
		try {
			List<Event> events = eventService.findAll();
			List<EventResponse> eventsResponse = events.stream().map(c -> eventAdapter.toEventResponse(c))
					.collect(Collectors.toList());
			LOGGER.info("Find all success");
			return ResponseEntity.ok(eventsResponse);

		} catch (EmptyListException e) {
			LOGGER.warn("Error, it couldn't list any event" + e.getMessage());
			throw e;
		}
	}

}