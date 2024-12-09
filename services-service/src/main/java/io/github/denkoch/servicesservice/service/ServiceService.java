package io.github.denkoch.servicesservice.service;

import io.github.denkoch.servicesservice.dto.ServiceDTO;
import io.github.denkoch.servicesservice.dto.ServiceResponseDTO;
import io.github.denkoch.servicesservice.model.Service;
import io.github.denkoch.servicesservice.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    public List<ServiceResponseDTO> getServices() {
        List<Service> servicesList = serviceRepository.findAll();

        return servicesList.stream()
                .map(item -> modelMapper.map(item, ServiceResponseDTO.class))
                .collect(Collectors.toList());
    }


}
