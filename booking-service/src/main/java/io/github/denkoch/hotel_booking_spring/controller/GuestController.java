package io.github.denkoch.hotel_booking_spring.controller;

import io.github.denkoch.hotel_booking_spring.dto.GuestDTO;
import io.github.denkoch.hotel_booking_spring.dto.GuestResponseDTO;
import io.github.denkoch.hotel_booking_spring.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getGuestsByIds(@RequestParam(name = "ids") List<Long> ids) {
        List<GuestResponseDTO> guestDTOs = guestService.getGuestsByIds(ids);
        return ResponseEntity.ok(guestDTOs);
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<GuestResponseDTO> getGuestsById(@PathVariable(name = "guestId") Long guestId) {
        GuestResponseDTO guest = guestService.getGuestsById(guestId);
        return ResponseEntity.ok(guest);
    }

}
