package io.github.denkoch.hotel_booking_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RoomBuildingsDTO {
    @NotBlank
    private String roomBuilding;

    @NotEmpty
    private List<Long> roomNumbers;
}
