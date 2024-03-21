package com.lucatic.grupo2.app.control;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.lucatic.grupo2.app.exceptions.EventException;
import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.model.adapter.EventAdapter;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.EventResponse;
import com.lucatic.grupo2.app.exceptions.EmptyListException;

import com.lucatic.grupo2.app.model.dto.EventResponseWithError;
import com.lucatic.grupo2.app.model.dto.EventResponseWithErrorList;
import com.lucatic.grupo2.app.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.ArraySchema;

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
	 * @param eventRequest Con los datos del Event a guardar
	 * @return ResponseEntity Con la respuesta de guardar el evento
	 * @throws EventExistException cuando no se guardo
	 */

	@Operation(summary = "Dar de alta un evento", description = "Incluye un nuevo evento en la base de datos", tags = {
			"event" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Evento creado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseWithError.class)) }),

			@ApiResponse(responseCode = "400", description = "El evento ya existe", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico en alta evento", content = @Content)

	})
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid EventRequest eventRequest) throws EventException {

		try {
			Event event = eventService.save(eventRequest);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId())
					.toUri();
			LOGGER.info("Event " + event.getName() + " with id " + event.getId() + " has been created");
			return ResponseEntity.created(location).body(eventAdapter.toEventResponseWithError(event));

		} catch (EventExistException e) {
			LOGGER.warn("Error pushing the event" + e.getMessage());
			throw e;
		} catch (EventException e) {
			LOGGER.warn("Error generico" + e.getMessage());
			throw e;
		}

	}

	/**
	 * Lista todos los eventos que estan guardados en la bbdd
	 * 
	 * @return ResponseEntity con el response de eventos
	 * @throws EmptyListException cuando no devuelve elementos de la lista eventos
	 */
	@Operation(summary = "Listar todos los eventos", description = "Devuelve un listado de todos los eventos existentes", tags = {
			"event" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Events listados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EventResponseWithErrorList.class)))),
			@ApiResponse(responseCode = "404", description = "No hay evenntos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico listando eventos", content = @Content)

	})
	/*
	 * @GetMapping("/all") public ResponseEntity<?> listAll() throws
	 * EmptyListException {
	 * 
	 * try { List<Event> events = eventService.findAll();
	 * List<EventResponseWithError> eventsResponseWithError = events.stream() .map(e
	 * -> eventAdapter.toEventResponseWithError(e)).collect(Collectors.toList());
	 * 
	 * LOGGER.info("Find all success"); return
	 * ResponseEntity.ok(eventsResponseWithError);
	 * 
	 * } catch (EmptyListException e) {
	 * LOGGER.warn("Error, it couldn't list any event" + e.getMessage()); throw e; }
	 * }
	 */
	@GetMapping("/all")
	public ResponseEntity<?> listAll() throws EmptyListException {

		try {
			List<Event> events = eventService.findAll();
			List<EventResponse> eventResponses = events.stream().map(u -> eventAdapter.toEventResponse(u))
					.collect(Collectors.toList());
			EventResponseWithErrorList eventResponseWithErrorList = new EventResponseWithErrorList();
			eventResponseWithErrorList.setEventResponse(eventResponses);
			LOGGER.info("Find all success");
			return ResponseEntity.ok(eventResponseWithErrorList);

		} catch (EmptyListException e) {
			LOGGER.warn("Error, it couldn't list any event" + e.getMessage());
			throw e;
		}
	}

	/**
	 * Comprueba si un evento existe por su ID.
	 *
	 * @param id ID del evento a comprobar
	 * @return ResponseEntity con el resultado de la comprobación
	 */
	@Operation(summary = "Comprobar si existe evento", description = "Devuelve un true si el evento ya existe y un false si no existe previamente", tags = {
			"event" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "El evento se ha encontrado", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EventResponseWithError.class)))),
			@ApiResponse(responseCode = "404", description = "No hay evento encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico comprobador existencia evento", content = @Content) })

	@GetMapping("/exists/{id}")
	public ResponseEntity<?> eventFindById(@PathVariable Long id) {
		if (eventService.eventFindById(id)) {
			LOGGER.info("Event found with id: " + id.toString());
			return ResponseEntity.ok(eventAdapter.toExitEventResponseWithError(true));
		} else {
			LOGGER.info("Event not found");
			return ResponseEntity.ok(eventAdapter.toExitEventResponseWithError(false));
		}

	}

	/**
	 * Modifica un request body de un evento existente en la bdd events
	 * 
	 * @param eventRequest Con los datos del Event a modificar
	 * @param id           ID del evento a modificar
	 * @return ResponseEntity Con la respuesta de editar del evento
	 * @throws EventException EventExistException cuando no se edito correctamente.
	 */
	@Operation(summary = "Modificar un evento", description = "Modifica un evento de la base de datos", tags = {
			"event" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Evento editado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseWithError.class)) }),

			@ApiResponse(responseCode = "400", description = "No hay evento encontrado para editar", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico en edicion de evento", content = @Content)

	})
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid EventRequest eventRequest, @PathVariable Long id)
			throws EventException {
		try {
			Event eventUpdated = eventService.update(eventRequest, id);
			LOGGER.info("Event " + eventUpdated.getName() + " with id " + eventUpdated.getId() + " has been updated");
			return ResponseEntity.ok(eventAdapter.toEventResponseWithError(eventUpdated));
		} catch (EventExistException e) {
			LOGGER.warn("Error putting the event" + e.getMessage());
			throw e;
		} catch (EventException e) {
			LOGGER.warn("Error generico" + e.getMessage());
			throw e;
		}
	}

	/**
	 * Elimina un evento existente en la bdd events
	 * 
	 * @param id ID del evento a modificar
	 * @return ResponseEntity Con el id del evento eliminado
	 * @throws EventException error generico, EventExistException no se encontro
	 *                        cuando no se elimino correctamente.
	 */
	@Operation(summary = "Eliminar un evento", description = "Elimina un evento de la base de datos", tags = {
			"event" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Evento eliminado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseWithError.class)) }),

			@ApiResponse(responseCode = "400", description = "No hay evento encontrado para eliminar", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico en borrado de evento", content = @Content)

	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws EventException {
		try {
			Event event = eventService.deleteById(id);
			LOGGER.info("Event " + event.getName() + " with id " + event.getId() + " has been deleted");
			return ResponseEntity.ok().body(eventAdapter.toEventResponseWithError(event));

		} catch (EventExistException e) {
			LOGGER.warn("Error deleting the event" + e.getMessage());
			throw e;
		} catch (EventException e) {
			LOGGER.warn("Error generico" + e.getMessage());
			throw e;
		}
	}

	/**
	 * Lista los eventos por nombre de evento guardados en la bbdd
	 * 
	 * @return ResponseEntity con el response de eventos
	 * @throws EmptyListException cuando no devuelve elementos de la lista
	 */
	@Operation(summary = "Listar los eventos por nombre", description = "Devuelve un listado los eventos con un nombre especifico", tags = {
			"event" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos listados con nombre concreto", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EventResponseWithErrorList.class)))),
			@ApiResponse(responseCode = "404", description = "No hay eventos con ese nombre", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico listando eventos", content = @Content)

	})
	@GetMapping("/{name}")
	public ResponseEntity<?> listEventByName(@Valid @PathVariable String name) throws EventException {
		try {
			List<Event> events = eventService.findByName(name);
			List<EventResponse> eventResponses = events.stream().map(u -> eventAdapter.toEventResponse(u))
					.collect(Collectors.toList());
			EventResponseWithErrorList eventResponseWithErrorList = new EventResponseWithErrorList();
			eventResponseWithErrorList.setEventResponse(eventResponses);
			LOGGER.info("Find all events with name: " + name);
			return ResponseEntity.ok(eventResponseWithErrorList);
		} catch (EmptyListException e) {
			LOGGER.warn("Error, it couldn't list any event" + e.getMessage());
			throw e;
		}
	}

	/**
	 * Lista los eventos por genero de evento guardados en la bbdd
	 * 
	 * @return ResponseEntity con el response de eventos
	 * @throws EmptyListException cuando no devuelve elementos de la lista
	 */
	@Operation(summary = "Listar los eventos por roomType", description = "Devuelve un listado los eventos de un genero concreto", tags = {
			"event" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eventos listados con tipo o genero concreto", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EventResponseWithErrorList.class)))),
			@ApiResponse(responseCode = "404", description = "No hay eventos con ese genero", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error genérico listando eventos", content = @Content)

	})
	@GetMapping("eventsbyroom/{roomType}")
	public ResponseEntity<?> listEventsByRoomType(@Valid @PathVariable String roomType) throws EventException {
		try {
			List<Event> events = eventService.findEventsByRoomType(roomType);
			List<EventResponse> eventResponses = events.stream().map(u -> eventAdapter.toEventResponse(u))
					.collect(Collectors.toList());
			EventResponseWithErrorList eventResponseWithErrorList = new EventResponseWithErrorList();
			eventResponseWithErrorList.setEventResponse(eventResponses);
			LOGGER.info("Find all events with roomType: " + roomType);
			return ResponseEntity.ok(eventResponseWithErrorList);
		} catch (EmptyListException e) {
			LOGGER.warn("Error, it couldn't list any event" + e.getMessage());
			throw e;
		}
	}

}