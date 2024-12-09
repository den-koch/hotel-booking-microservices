package io.github.denkoch.hotel_booking_spring.repository;

import io.github.denkoch.hotel_booking_spring.model.Booking;
import io.github.denkoch.hotel_booking_spring.model.RoomId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.sql.Date;
import java.util.Collection;

public interface BookingRepository extends ListCrudRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN b.room r " +
            "WHERE r.roomId.roomBuilding = ?1 " +
            "ORDER BY b.checkoutDate, b.checkinDate")
    Collection<Booking> findBookingsByRoomBuilding(String buildingId);

    @Query("SELECT b FROM Booking b JOIN b.room r " +
            "WHERE r.roomId = ?1 " +
            "AND (((?2 >= b.checkinDate AND ?2 < b.checkoutDate) OR (?3 > b.checkinDate AND ?3 <= b.checkoutDate)) " +
            "OR (?2 < b.checkinDate AND ?3 > b.checkoutDate))")
    Collection<Booking> findBookedRoomsByIdAndDate(RoomId roomId, Date checkinDate, Date checkoutDate);

    @Query("SELECT b1 FROM Booking b1 JOIN Booking b2 ON b1.room.roomId = b2.room.roomId " +
            "WHERE b1.guest.guestId <> b2.guest.guestId AND b1.checkinDate < b2.checkoutDate " +
            "AND b1.checkoutDate > b2.checkinDate")
    Collection<Booking> findBookingsConflicts();

    @Query("SELECT b FROM Booking b WHERE b.checkoutDate < ?1")
    Collection<Booking> findOutdatedBookings(Date todaysDate);

}
