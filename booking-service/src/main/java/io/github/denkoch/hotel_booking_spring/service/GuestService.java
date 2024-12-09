package io.github.denkoch.hotel_booking_spring.service;

import io.github.denkoch.hotel_booking_spring.dto.GuestDTO;
import io.github.denkoch.hotel_booking_spring.dto.GuestResponseDTO;
import io.github.denkoch.hotel_booking_spring.model.Guest;
import io.github.denkoch.hotel_booking_spring.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    public List<GuestResponseDTO> getGuestsByIds(List<Long> ids) {
        List<Guest> guests = guestRepository.findAllById(ids);
        return guests.stream().map(guest -> modelMapper.map(guest, GuestResponseDTO.class))
                .collect(Collectors.toList());
    }

    public GuestResponseDTO getGuestsById(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new RuntimeException("Guest {guestId "+ guestId +"} not found"));
        return modelMapper.map(guest, GuestResponseDTO.class);
    }



    public Optional<Guest> getGuestByEmail(GuestDTO guestDTO) {
        return guestRepository.findByEmail(guestDTO.getEmail());
    }

    @Transactional
    public Guest createGuest(GuestDTO guestDTO) {
        Guest guest = modelMapper.map(guestDTO, Guest.class);

        guest = guestRepository.save(guest);

        return guest;
    }


}
