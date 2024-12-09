package io.github.denkoch.servicesservice.service;

import io.github.denkoch.hotel_booking_spring.exceptions.ResourceNotFoundException;
import io.github.denkoch.servicesservice.dto.*;
import io.github.denkoch.servicesservice.model.Service;
import io.github.denkoch.servicesservice.model.ServiceHistory;
import io.github.denkoch.servicesservice.repository.ServiceHistoryRepository;
import io.github.denkoch.servicesservice.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceHistoryService {

//    private final String GUESTS_URL = "http://localhost:8081/api/guests?ids=";
//    private final String GUEST_URL = "http://localhost:8081/api/guests/";

    private final String GUESTS_URL = "http://booking-service:8081/api/guests?ids=";
    private final String GUEST_URL = "http://booking-service:8081/api/guests/";


    private final ServiceHistoryRepository serviceHistoryRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final ServiceRepository serviceRepository;

    public List<ServiceHistoryResponseDTO> getServiceHistory() {

        List<ServiceHistory> serviceHistoryList = serviceHistoryRepository.findAll();

        List<Long> guestIds = serviceHistoryList.stream()
                .map(ServiceHistory::getGuestId).collect(Collectors.toList());

        Map<Long, GuestDTO> guestDTOMap = fetchGuestDTOs(guestIds);

        return serviceHistoryList.stream()
                .map(item -> {
                    ServiceHistoryResponseDTO serviceHistoryResponseDTO = modelMapper.map(item, ServiceHistoryResponseDTO.class);
                    GuestDTO guestDTO = guestDTOMap.get(item.getGuestId());
                    serviceHistoryResponseDTO.setGuest(guestDTO);
                    ServiceResponseDTO serviceDTO = modelMapper.map(item.getService(), ServiceResponseDTO.class);
                    serviceHistoryResponseDTO.setService(serviceDTO);
                    return serviceHistoryResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public ServiceHistoryResponseDTO createServiceHistory(ServiceHistoryCreationDTO serviceHistoryCreationDTO) {

        Long guestId = serviceHistoryCreationDTO.getGuestId();

        GuestDTO guest = fetchGuestDTO(guestId);

        String serviceId = serviceHistoryCreationDTO.getServiceId();
        Service service = serviceRepository.findById(serviceId).orElseThrow(()->
                new ResourceNotFoundException("Service {serviceId " + serviceId + " not found"));

        ServiceHistory serviceHistory = ServiceHistory.builder()
                .guestId(guestId)
                .service(service)
                .serviceDate(serviceHistoryCreationDTO.getServiceDate())
                .build();

        serviceHistory = serviceHistoryRepository.save(serviceHistory);

        ServiceHistoryResponseDTO serviceHistoryResponse = modelMapper.map(serviceHistory, ServiceHistoryResponseDTO.class);

        serviceHistoryResponse.setGuest(guest);

        return serviceHistoryResponse;
    }

    public void deleteServiceHistoryRecord(String serviceHistoryId) {
        ServiceHistory serviceHistory = serviceHistoryRepository.findById(serviceHistoryId).orElse(null);
        serviceHistoryRepository.delete(serviceHistory);
    }

    private Map<Long, GuestDTO> fetchGuestDTOs(List<Long> guestIds) {

        String ids = guestIds.stream()
                .map(String::valueOf).collect(Collectors.joining(","));

        String url = GUESTS_URL + ids;

        GuestDTO[] guestDTOArray = restTemplate.getForObject(url, GuestDTO[].class);

        return Arrays.stream(guestDTOArray)
                .collect(Collectors.toMap(GuestDTO::getGuestId, guestDTO -> guestDTO));
    }

    private GuestDTO fetchGuestDTO(Long guestId) {
        String url = GUEST_URL + guestId;
        return restTemplate.getForObject(url, GuestDTO.class);
    }

}
