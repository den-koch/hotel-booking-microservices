package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "service_name", nullable = false, unique = true)
    private String serviceName;

    @Column(name = "service_cost")
    private Long serviceCost;

    @OneToMany(mappedBy = "service")
    private Collection<ServiceHistory> serviceHistory;
}
