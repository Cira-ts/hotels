package com.hotels.booking.hotel.controller.dto;

import com.hotels.booking.hotel.repository.enums.City;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record HotelCreateDto (
        @NotEmpty
        String name,
        City city,
        @NotEmpty
        String website,
        String phone,
        @Email
        String email,
        BigDecimal price,
        @NotEmpty
        boolean active
){

}
