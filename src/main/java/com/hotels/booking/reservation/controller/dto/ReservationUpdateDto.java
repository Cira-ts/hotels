package com.hotels.booking.reservation.controller.dto;

import com.hotels.booking.room.repository.entity.Room;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationUpdateDto (
        LocalDate checkInDate,
        LocalDate checkOutDate
){
}
