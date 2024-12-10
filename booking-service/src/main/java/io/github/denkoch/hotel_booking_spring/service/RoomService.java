package io.github.denkoch.hotel_booking_spring.service;

import io.github.denkoch.hotel_booking_spring.dto.RoomBuildingsDTO;
import io.github.denkoch.hotel_booking_spring.dto.RoomDTO;
import io.github.denkoch.hotel_booking_spring.model.Room;
import io.github.denkoch.hotel_booking_spring.model.RoomId;
import io.github.denkoch.hotel_booking_spring.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public List<RoomBuildingsDTO> getBuildingRooms() {
        Map<String, List<Long>> buildingRoomsListMap = roomRepository.findRoomIds().stream()
                .collect(Collectors.groupingBy(
                        RoomId::getRoomBuilding,
                        Collectors.mapping(RoomId::getRoomNumber, Collectors.toList())
                ));

        return buildingRoomsListMap.entrySet().stream()
                .map(entry -> new RoomBuildingsDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getRoomsByBuildingAndFilters(String buildingId, Long roomCapacity,
                                                      Long roomPrice, String roomType) {
        Collection<Room> roomsByBuilding = roomRepository.findRoomsByRoomBuilding(buildingId);

        Collection<Room> filteredRooms = roomsByBuilding;

        if (roomCapacity != null) {
            filteredRooms = filteredRooms.stream()
                    .filter(room -> room.getCapacity() <= roomCapacity).toList();
        }
        if (roomPrice != null) {
            filteredRooms = filteredRooms.stream()
                    .filter(room -> room.getPrice() <= roomPrice).toList();
        }
        if (roomType != null) {
            filteredRooms = filteredRooms.stream()
                    .filter(room -> room.getRoomType().getRoomType().equals(roomType)).toList();
        }

        return filteredRooms.stream().map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }


}
