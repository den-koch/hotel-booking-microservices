package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "service_history")
public class ServiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_history_id")
    private Long serviceHistoryId;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private Service service;

//    @NotNull
    @Column(name = "service_date", nullable = false)
    private Date serviceDate;

}
