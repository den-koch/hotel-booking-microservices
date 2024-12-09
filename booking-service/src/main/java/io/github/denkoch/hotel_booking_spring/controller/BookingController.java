package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.BookingCreationDTO;
import io.github.denkoch.hotel_booking_spring.dto.BookingDetailsDTO;
import io.github.denkoch.hotel_booking_spring.dto.BookingSummaryDTO;
import io.github.denkoch.hotel_booking_spring.model.Booking;
import io.github.denkoch.hotel_booking_spring.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final ModelMapper modelMapper;

//    @GetMapping
//    public ResponseEntity<List<BookingSummaryDTO>> getBookings() {
//        List<BookingSummaryDTO> bookings = bookingService.getAllBookings();
//        return ResponseEntity.ok(bookings);
//    }

    @GetMapping
    public ResponseEntity<List<BookingSummaryDTO>> getBookingByBuilding(@RequestParam String buildingId) {
        List<BookingSummaryDTO> bookings = bookingService.getBookingByBuilding(buildingId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> getBookingDetail(@PathVariable Long bookingId) {
        Optional<BookingDetailsDTO> booking = bookingService.getBookingDetail(bookingId);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookingDetailsDTO> postBooking(@RequestBody @Valid BookingCreationDTO bookingCreationDTO) {

        Booking booking = bookingService.createBooking(bookingCreationDTO);

        BookingDetailsDTO bookingDetails = modelMapper.map(booking, BookingDetailsDTO.class);

        if (bookingDetails == null) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bookingId}")
                .buildAndExpand(bookingDetails.getBookingId()).toUri();

        return ResponseEntity.created(uri).body(bookingDetails);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> putBooking(@PathVariable Long bookingId,
                                                        @RequestBody @Valid BookingCreationDTO bookingCreationDTO) {
        Booking booking = bookingService.updateBooking(bookingId, bookingCreationDTO);
        BookingDetailsDTO bookingDetailsDTO = modelMapper.map(booking, BookingDetailsDTO.class);
        return ResponseEntity.ok(bookingDetailsDTO);
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
