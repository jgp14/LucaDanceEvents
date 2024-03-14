package com.lucatic.grupo2.app.repository;

import com.lucatic.grupo2.app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
