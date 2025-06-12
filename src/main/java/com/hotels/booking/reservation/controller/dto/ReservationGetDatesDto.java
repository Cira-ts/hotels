package com.hotels.booking.reservation.controller.dto;

import java.time.LocalDate;

public interface ReservationGetDatesDto {
    LocalDate getCheckInDate();
    LocalDate getCheckOutDate();
}
