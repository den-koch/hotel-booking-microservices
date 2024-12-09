package io.github.denkoch.hotel_booking_spring.service;

import io.github.denkoch.hotel_booking_spring.dto.BookingCreationDTO;
import io.github.denkoch.hotel_booking_spring.dto.BookingDetailsDTO;
import io.github.denkoch.hotel_booking_spring.dto.BookingSummaryDTO;
import io.github.denkoch.hotel_booking_spring.exceptions.ResourceAlreadyExistsException;
import io.github.denkoch.hotel_booking_spring.exceptions.ResourceNotFoundException;
import io.github.denkoch.hotel_booking_spring.model.*;
import io.github.denkoch.hotel_booking_spring.repository.BookingRepository;
import io.github.denkoch.hotel_booking_spring.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final GuestService guestService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;

    public List<BookingSummaryDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream().map(booking -> modelMapper.map(booking, BookingSummaryDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookingSummaryDTO> getBookingByBuilding(String buildingId) {
        Collection<Booking> bookingsByBuilding = bookingRepository.findBookingsByRoomBuilding(buildingId);
        return bookingsByBuilding.stream().map(booking -> modelMapper.map(booking, BookingSummaryDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<BookingDetailsDTO> getBookingDetail(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> modelMapper.map(booking, BookingDetailsDTO.class));
    }

    @Transactional
    public Booking createBooking(BookingCreationDTO bookingCreationDTO) {
        Booking booking = modelMapper.map(bookingCreationDTO, Booking.class);

        RoomId roomId = booking.getRoom().getRoomId();

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Room { roomNumber = %s, roomBuilding = %s } not found",
                                roomId.getRoomNumber(), roomId.getRoomBuilding())));

        Collection<Booking> roomBookings = bookingRepository.findBookedRoomsByIdAndDate(roomId,
                booking.getCheckinDate(), booking.getCheckoutDate());
        if (!roomBookings.isEmpty()) {
            throw new ResourceAlreadyExistsException(
                    String.format("Booking on {checkinDate = %s, checkoutDate = %s} already exists",
                            booking.getCheckinDate(), booking.getCheckoutDate()));
        }

        Guest guest = guestService.getGuestByEmail(bookingCreationDTO.getGuest()).orElse(null);
        if (guest == null) {
            guest = guestService.createGuest(bookingCreationDTO.getGuest());
        }

        Company company = null;
        if (bookingCreationDTO.getCompany() != null) {
            company = companyService.getCompanyByEmail(bookingCreationDTO.getCompany()).orElse(null);
        }

        booking.setGuest(guest);
        booking.setCompany(company);

        booking = bookingRepository.save(booking);

        return booking;
    }

    @Transactional
    public Booking updateBooking(Long bookingId, BookingCreationDTO bookingCreationDTO) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking { bookingId = " + bookingId + "} not found"));

        Booking bookingCreation = modelMapper.map(bookingCreationDTO, Booking.class);

        Collection<Booking> roomBookings =
                bookingRepository.findBookedRoomsByIdAndDate(bookingCreation.getRoom().getRoomId(),
                        bookingCreation.getCheckinDate(), bookingCreation.getCheckoutDate());
        roomBookings.remove(booking);

        if (!roomBookings.isEmpty()) {
            throw new ResourceAlreadyExistsException(
                    String.format("Booking on {checkinDate = %s, checkoutDate = %s} already exists",
                            booking.getCheckinDate(), booking.getCheckoutDate()));
        }

        booking.setCheckinDate(bookingCreation.getCheckinDate());
        booking.setCheckoutDate(bookingCreation.getCheckoutDate());

        booking = bookingRepository.save(booking);

        return booking;
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

}
