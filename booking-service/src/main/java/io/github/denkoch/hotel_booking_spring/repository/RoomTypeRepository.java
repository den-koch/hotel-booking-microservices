package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.RoomType;
import org.springframework.data.repository.CrudRepository;

public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
}
