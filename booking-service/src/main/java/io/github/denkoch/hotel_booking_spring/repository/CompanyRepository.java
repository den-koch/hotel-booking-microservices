package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.Company;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findByCompanyEmail(String email);

}
