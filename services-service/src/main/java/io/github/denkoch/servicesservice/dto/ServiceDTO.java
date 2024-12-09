package io.github.denkoch.servicesservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class ServiceDTO {

    @NotBlank
    private String serviceName;

    private Long serviceCost;
}
