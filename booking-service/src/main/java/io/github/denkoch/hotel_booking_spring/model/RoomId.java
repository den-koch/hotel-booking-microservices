package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
//@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RoomId implements Serializable {

    @Column(name = "room_number")
    private Long roomNumber;

    @Column(name = "room_building")
    private String roomBuilding;
}
