package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.ServiceHistory;
import org.springframework.data.repository.CrudRepository;

public interface ServiceHistoryRepository extends CrudRepository<ServiceHistory, Long> {
}
