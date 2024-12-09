package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends ListCrudRepository<Guest, Long> {

    Optional<Guest> findByEmail(String email);
}
