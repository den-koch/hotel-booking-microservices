package io.github.denkoch.hotel_booking_spring.dto;

import io.github.denkoch.hotel_booking_spring.config.validator.FutureOrPresentSqlDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class BookingCreationDTO {

    @NotNull
    private RoomDTO room;

    @FutureOrPresentSqlDate
    private Date checkinDate;

    @FutureOrPresent
    private Date checkoutDate;

    @NotNull
    private GuestDTO guest;

    private CompanyDTO company;
}
