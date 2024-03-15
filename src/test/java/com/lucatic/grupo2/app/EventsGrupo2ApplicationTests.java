package com.lucatic.grupo2.app;

import com.lucatic.grupo2.app.model.Event;
import com.lucatic.grupo2.app.repository.EventRepository;
import com.lucatic.grupo2.app.service.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventsGrupo2ApplicationTests {

	@Autowired
	private EventService eventService;

	@MockBean
	private EventRepository eventRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void listarAllConListaVacia()  {

		List<Event> events = new ArrayList<>();

		when(eventRepository.findAll()).thenReturn(events);

		assertEquals(events, eventRepository.findAll());
	}

}
