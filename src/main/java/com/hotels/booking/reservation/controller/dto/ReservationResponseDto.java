package com.hotels.booking.reservation.controller.dto;

import com.hotels.booking.reservation.repository.enums.ReservationStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ReservationResponseDto (
        Long roomId,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        BigDecimal totalPrice,
        ReservationStatus status
){
}
