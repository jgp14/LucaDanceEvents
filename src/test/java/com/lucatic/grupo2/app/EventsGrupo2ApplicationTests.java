package com.lucatic.grupo2.app;

import com.lucatic.grupo2.app.exceptions.EmptyListException;
import com.lucatic.grupo2.app.exceptions.EventException;
import com.lucatic.grupo2.app.exceptions.EventExistException;
import com.lucatic.grupo2.app.model.Room;
import com.lucatic.grupo2.app.model.dto.EventRequest;
import com.lucatic.grupo2.app.model.dto.RoomRequest;
import com.lucatic.grupo2.app.repository.EventRepository;
import com.lucatic.grupo2.app.service.EventService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de test
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@SpringBootTest
class EventsGrupo2ApplicationTests {

	/** Inyectar servicio */
	@Autowired
	private EventService eventService;

	/** Inyectar repositorio */
	@Autowired
	private EventRepository eventRepository;

	/**
	 * Funciona test
	 */
	@Test
	void contextLoads() {
		Assertions.assertThat(true).isTrue();
	}

	/**
	 * Test listar todos con lista vacía
	 */
	@Test
	public void listAllWithEmptyList() {

		assertDoesNotThrow(() -> eventService.findAll());
	}

	/**
	 * Test listar todos con base de datos no vacía
	 * @throws EmptyListException Si la lista está vacía
	 */
	@Test
	public void ListAllWithNotEmptyList() throws EmptyListException {

		assertFalse(eventService.findAll().isEmpty());
	}

	/**
	 * Salvar event if le pasamos un null
	 */
	@Test
	public void addEventIfNull() {

		assertThrows(EventException.class, () -> eventService.save(null));
	}

	/**
	 * Salvar event si no existe el evento
	 * @throws EventException
	 */
	@Test
	public void addEventIfNoExist() throws EventException {

		EventRequest eventRequest = new EventRequest(
				0L,
				"YYY",
				"YYY",
				"YYY",
				"YYY",
				"01-01-2000",
				"01-01-2000",
				"17:00",
				"FULLPASS",
				"aaa",
				Arrays.asList(new RoomRequest(
						new Room("aaa", "aaa", "aaa", "aaa", 100),
						"01-01-2000",
						"17:00",
						"17:00"
				))
		);

		assertEquals("YYY", eventService.save(eventRequest).getName());
	}

	/**
	 * Salvar event si existe el evento
	 */
	@Test
	public void addEventIfExist() {

		EventRequest eventRequest = new EventRequest(
				5L,
				"YYY",
				"YYY",
				"YYY",
				"YYY",
				"01-01-2000",
				"01-01-2000",
				"17:00",
				"FULLPASS",
				"aaa",
				Arrays.asList(new RoomRequest(
						new Room("aaa", "aaa", "aaa", "aaa", 100),
						"01-01-2000",
						"17:00",
						"17:00"
				))
		);

		assertThrows(EventExistException.class, () -> eventService.save(eventRequest));

	}
}