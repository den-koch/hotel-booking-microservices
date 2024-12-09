package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.Room;
import io.github.denkoch.hotel_booking_spring.model.RoomId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RoomRepository extends CrudRepository<Room, RoomId> {

    @Query("SELECT r.roomId FROM Room r")
    Collection<RoomId> findRoomIds();


    @Query("SELECT r FROM Room r JOIN r.roomType rt " +
            "WHERE r.roomId.roomBuilding = ?1")
    Collection<Room> findRoomsByRoomBuilding(String buildingId);
}
