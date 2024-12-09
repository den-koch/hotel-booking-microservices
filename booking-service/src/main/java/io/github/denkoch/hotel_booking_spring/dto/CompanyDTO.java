package io.github.denkoch.hotel_booking_spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDTO {

    @NotBlank
    private String companyName;

    @Email
    private String companyEmail;

    private String companyPhoneNumber;
}
