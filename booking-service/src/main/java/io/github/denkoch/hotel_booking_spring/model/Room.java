package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room {

    @EmbeddedId
    private RoomId roomId;

    @Column(name = "capacity")
    private Long capacity;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private Collection<Booking> bookings;

    @OneToMany(mappedBy = "room")
    private Collection<History> history;

}
