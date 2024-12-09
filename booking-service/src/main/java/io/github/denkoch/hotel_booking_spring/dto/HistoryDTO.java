package io.github.denkoch.hotel_booking_spring.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class HistoryDTO {

    @NotNull
    private RoomIdDTO roomId;

    @PastOrPresent
    private Date checkinDate;

    @PastOrPresent
    private Date checkoutDate;

    @NotNull
    private GuestDTO guest;

    private CompanyDTO company;

    private String feedback;

}
