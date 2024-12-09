package io.github.denkoch.servicesservice.controller;

import io.github.denkoch.servicesservice.dto.ServiceHistoryCreationDTO;
import io.github.denkoch.servicesservice.dto.ServiceHistoryResponseDTO;
import io.github.denkoch.servicesservice.service.ServiceHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-history")
public class ServiceHistoryController {

    private final ServiceHistoryService serviceHistoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ServiceHistoryResponseDTO>> getServiceHistory() {
        List<ServiceHistoryResponseDTO> serviceHistoryResponseDTOs = serviceHistoryService.getServiceHistory();
        return ResponseEntity.ok(serviceHistoryResponseDTOs);
    }

    @PostMapping
    public ResponseEntity<ServiceHistoryResponseDTO> createServiceHistoryRecord(
            @RequestBody @Valid ServiceHistoryCreationDTO serviceHistoryCreationDTO) {

        ServiceHistoryResponseDTO serviceHistoryResponse = serviceHistoryService.createServiceHistory(serviceHistoryCreationDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{serviceHistoryId}")
                .buildAndExpand(serviceHistoryResponse.getServiceHistoryId()).toUri();

        return ResponseEntity.created(uri).body(serviceHistoryResponse);
    }

    @DeleteMapping("/{serviceHistoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceHistoryRecord(@PathVariable String serviceHistoryId) {
        serviceHistoryService.deleteServiceHistoryRecord(serviceHistoryId);
    }


}
