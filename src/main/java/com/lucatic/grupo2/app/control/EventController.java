package com.lucatic.grupo2.app.control;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.adapter.EventAdapter;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.EventResponse;

import com.lucatic.grupo2.app.exceptions.EmptyListException;

import com.lucatic.grupo2.app.model.dto.EventResponseWithError;

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

/**
 * Clase controladora, toma las decisiones de la aplicacion
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
@RestController
@RequestMapping("/event")
class EventController {

	/**
	 * Logger que registra los errores de clase EventController
	 */
	private final static Logger LOGGER = LogManager.getLogger(EventController.class);

	/**
	 * Auto instanciamos el servicio de eventos
	 */
	@Autowired
	private EventService eventService;

	/**
	 * Adaptador con transformacion de envento entity a response y request to entity
	 */
	@Autowired
	private EventAdapter eventAdapter;

	/**
	 * Guarda un request body de un nuevo evento en la bbdd events
	 * 
	 * @param eventRequest
	 * @return ResponseEntity
	 * @throws EventExistException cuando no se guardo
	 */
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

	/**
	 * Lista todos los eventos que estan guardados en la bbdd
	 * 
	 * @return ResponseEntity con el response de eventos
	 * @throws EmptyListException cuando no devuelve elementos de la lista eventos
	 */
	@GetMapping("/all")
	public ResponseEntity<?> listAll() throws EmptyListException {
		// return productAdapter.convertToDto(productService.getAll());
		try {
			List<Event> events = eventService.findAll();
			List<EventResponseWithError> eventsResponseWithError = events.stream().map(c -> eventAdapter.toEventResponseWithError(c))
					.collect(Collectors.toList());
			LOGGER.info("Find all success");
			return ResponseEntity.ok(eventsResponseWithError);

		} catch (EmptyListException e) {
			LOGGER.warn("Error, it couldn't list any event" + e.getMessage());
			throw e;
		}
	}

}