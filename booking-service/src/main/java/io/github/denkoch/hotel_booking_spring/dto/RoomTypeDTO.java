package io.github.denkoch.hotel_booking_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RoomTypeDTO {

    @NotBlank
    private String roomTypeId;

    @NotBlank
    private String roomType;

    @PositiveOrZero
    private Long costPerDay;
}
