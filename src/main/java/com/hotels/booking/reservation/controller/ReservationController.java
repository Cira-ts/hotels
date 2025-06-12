package com.hotels.booking.reservation.controller;

import com.hotels.booking.reservation.controller.dto.ReservationCreateDto;
import com.hotels.booking.reservation.controller.dto.ReservationGetDatesDto;
import com.hotels.booking.reservation.controller.dto.ReservationResponseDto;
import com.hotels.booking.reservation.controller.dto.ReservationUpdateDto;
import com.hotels.booking.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> createReservation(@RequestBody ReservationCreateDto dto) {
        service.makeReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation successfully created.");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody ReservationUpdateDto dto) {
        service.updateReservation(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body("Reservation successfully updated.");
    }

    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id){
        service.cancelReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body("Reservation successfully cancelled.");
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<ReservationResponseDto> getAllReservationsByUser() {
        return service.getAllReservationsByUser();
    }

    @GetMapping("/{id}/reserved-dates")
    @PreAuthorize("hasRole('USER')")
    public List<ReservationGetDatesDto> getReservedDates(@PathVariable Long id) {
        return service.getRoomReservedDates(id);
    }

}
