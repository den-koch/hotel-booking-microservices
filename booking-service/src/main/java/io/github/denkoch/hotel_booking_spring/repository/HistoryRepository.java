package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface HistoryRepository extends ListCrudRepository<History, Long> {

    @Query("SELECT h FROM History h ORDER BY h.checkoutDate DESC , h.checkinDate DESC")
    List<History> findAll();
}
