package io.github.denkoch.servicesservice.dto;

import io.github.denkoch.servicesservice.config.validator.FutureOrPresentSqlDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ServiceHistoryCreationDTO {

    @NotNull
    private Long guestId;

    @NotNull
    private String serviceId;

    @FutureOrPresent
    private Date serviceDate;

}
