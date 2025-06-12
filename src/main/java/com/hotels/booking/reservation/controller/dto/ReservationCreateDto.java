package com.hotels.booking.reservation.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationCreateDto (
        @NotNull
        Long roomId,
        @NotNull
        LocalDate checkInDate,
        @NotNull
        LocalDate checkOutDate
)
{
}
