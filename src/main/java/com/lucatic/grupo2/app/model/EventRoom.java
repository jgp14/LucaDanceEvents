package com.lucatic.grupo2.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase modelo entidad intermedio de EventRoom para el registro de eventos y
 * salas en un momento concreto. Posee los getters, setters y constuctores
 * generados por Lombok.
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 15-03-2024
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_room")
public class EventRoom {

    /**
     * Atributo Long id autoincremental de los eventos_salas.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo Event que recoge el evento y su clave forarena. Esta
     * mapeado @ManyToOne
     */
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Event event;

    /**
     * Atributo Room que recoge la sala y su clave forarena. Esta mapeado @OneToOne
     * con limitacion @JsonManagedReference
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Room room;

    private LocalDate date;

    private LocalTime initTime;

    private LocalTime endTime;

    /**
     * Constructor EventRoom
     *
     * @param event Entidad con el evento introducido.
     * @param room  Entidad con la sala introducido.
     */
    public EventRoom(Event event, Room room) {
        this.event = event;
        this.room = room;
    }

    /**
     * Constructor EventRoom
     *
     * @param event Entidad con el evento introducido.
     */
    public EventRoom(Event event) {
        this.event = event;
    }

    public EventRoom(LocalTime initTime, LocalTime endTime) {
        this.initTime = initTime;
        this.endTime = endTime;
    }

    /**
     * Constructor con parámetros
     * @param event El evento
     * @param room La sala
     * @param date La fecha
     * @param initTime La hora de inicio
     * @param endTime la hora de fin
     */
    public EventRoom(Event event, Room room, LocalDate date, LocalTime initTime, LocalTime endTime) {
        this.event = event;
        this.room = room;
        this.date = date;
        this.initTime = initTime;
        this.endTime = endTime;
    }
}
