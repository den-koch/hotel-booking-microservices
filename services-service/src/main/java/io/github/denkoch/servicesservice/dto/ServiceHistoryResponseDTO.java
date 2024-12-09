package io.github.denkoch.servicesservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceHistoryResponseDTO {

    private String serviceHistoryId;

    private GuestDTO guest;

    private ServiceResponseDTO service;

    private Date serviceDate;

}
