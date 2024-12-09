package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "checkin_date", nullable = false)
    private Date checkinDate;

    @Column(name = "checkout_date", nullable = false)
    private Date checkoutDate;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "room_number", referencedColumnName = "room_number"),
            @JoinColumn(name = "room_building", referencedColumnName = "room_building")
    })
    private Room room;

}
