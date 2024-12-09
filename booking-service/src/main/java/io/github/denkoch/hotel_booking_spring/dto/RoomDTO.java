package io.github.denkoch.hotel_booking_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RoomDTO {

    @NotNull
    private RoomIdDTO roomId;

    private RoomTypeDTO roomType;

    @Positive
    private Long capacity;

    @PositiveOrZero
    private Long price;

}
