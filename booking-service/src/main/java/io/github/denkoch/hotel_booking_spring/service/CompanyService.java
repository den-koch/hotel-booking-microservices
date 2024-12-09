package io.github.denkoch.hotel_booking_spring.service;

import io.github.denkoch.hotel_booking_spring.dto.CompanyDTO;
import io.github.denkoch.hotel_booking_spring.dto.GuestDTO;
import io.github.denkoch.hotel_booking_spring.model.Company;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import io.github.denkoch.hotel_booking_spring.repository.CompanyRepository;
import io.github.denkoch.hotel_booking_spring.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public Optional<Company> getCompanyByEmail(CompanyDTO companyDTO) {
        return companyRepository.findByCompanyEmail(companyDTO.getCompanyEmail());
    }

}
