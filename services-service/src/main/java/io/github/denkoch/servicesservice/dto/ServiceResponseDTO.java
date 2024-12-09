package io.github.denkoch.servicesservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceResponseDTO {

    private String serviceId;

    private String serviceName;

    private Long serviceCost;
}

