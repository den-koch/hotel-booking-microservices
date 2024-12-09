package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "room_types")
public class RoomType {

    @Id
    @Column(name = "room_type_id")
    private String roomTypeId;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "cost_per_day")
    private Long costPerDay;

    @OneToMany(mappedBy = "roomType")
    private Collection<Room> rooms;


}
