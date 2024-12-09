package io.github.denkoch.hotel_booking_spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_email", nullable = false, unique = true)
    private String companyEmail;

    @Column(name = "company_phone")
    private String companyPhoneNumber;

    @OneToMany(mappedBy = "company")
    private Collection<History> history;

    @OneToMany(mappedBy = "company")
    private Collection<Booking> booking;

}
