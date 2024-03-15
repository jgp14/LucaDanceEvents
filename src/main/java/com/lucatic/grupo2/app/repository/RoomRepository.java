package com.lucatic.grupo2.app.repository;

import com.lucatic.grupo2.app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Genera una interfaz que hereda metodos de accion en la base de datos, gestiona objetos tipo Room
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByNameAndAddress(String name, String address);
}
