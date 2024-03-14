package com.lucatic.grupo2.app.repository;

import com.lucatic.grupo2.app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByNameAndAddress(String name, String address);
}
