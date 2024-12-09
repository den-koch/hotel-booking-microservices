package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.RoomBuildingsDTO;
import io.github.denkoch.hotel_booking_spring.dto.RoomDTO;
import io.github.denkoch.hotel_booking_spring.dto.RoomTypeDTO;
import io.github.denkoch.hotel_booking_spring.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/api/home")
    public ResponseEntity<List<RoomBuildingsDTO>> getBuildingRooms() {
        List<RoomBuildingsDTO> roomsBuildings = roomService.getBuildingRooms();
        return ResponseEntity.ok(roomsBuildings);
    }

    @GetMapping("/api/rooms")
    public ResponseEntity<List<RoomDTO>> getRoomTypes(@RequestParam String buildingId,
                                                      @RequestParam(required = false) Long roomCapacity,
                                                      @RequestParam(required = false) Long roomPrice,
                                                      @RequestParam(required = false) String roomType) {
        List<RoomDTO> rooms = roomService.getRoomsByBuildingAndFilters(buildingId, roomCapacity, roomPrice, roomType);
        return ResponseEntity.ok(rooms);
    }

}
