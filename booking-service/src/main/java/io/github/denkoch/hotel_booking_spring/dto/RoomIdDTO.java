package io.github.denkoch.hotel_booking_spring.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomIdDTO {

    @NotNull
    private Long roomNumber;

    @NotBlank
    private String roomBuilding;

}
