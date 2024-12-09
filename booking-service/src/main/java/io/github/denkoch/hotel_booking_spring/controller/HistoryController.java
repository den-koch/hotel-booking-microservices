package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.HistoryDTO;
import io.github.denkoch.hotel_booking_spring.service.BookingService;
import io.github.denkoch.hotel_booking_spring.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<HistoryDTO>> getHistory() {
        List<HistoryDTO> history = historyService.getHistory();
        return ResponseEntity.ok(history);
    }

}
