package io.github.denkoch.hotel_booking_spring.service;

import io.github.denkoch.hotel_booking_spring.dto.BookingSummaryDTO;
import io.github.denkoch.hotel_booking_spring.dto.HistoryDTO;
import io.github.denkoch.hotel_booking_spring.model.Booking;
import io.github.denkoch.hotel_booking_spring.model.History;
import io.github.denkoch.hotel_booking_spring.repository.BookingRepository;
import io.github.denkoch.hotel_booking_spring.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public List<HistoryDTO> getHistory() {
        return historyRepository.findAll()
                .stream().map(history -> modelMapper.map(history, HistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void archiveAndDeleteOutdatedBookings() {
        Collection<Booking> bookings = bookingRepository.findOutdatedBookings(Date.valueOf(LocalDate.now()));

        bookings.forEach(booking -> {
            History history = modelMapper.map(booking, History.class);
            historyRepository.save(history);
            bookingRepository.deleteById(booking.getBookingId());
        });

    }
}
