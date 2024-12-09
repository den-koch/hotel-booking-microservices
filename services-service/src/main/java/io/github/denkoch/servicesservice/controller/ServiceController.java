package io.github.denkoch.servicesservice.controller;

import io.github.denkoch.servicesservice.dto.ServiceDTO;
import io.github.denkoch.servicesservice.dto.ServiceResponseDTO;
import io.github.denkoch.servicesservice.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getServices() {
        List<ServiceResponseDTO> serviceDTOs = serviceService.getServices();
        return ResponseEntity.ok(serviceDTOs);
    }


}
