package com.hotels.booking.hotel.controller.dto;

import com.hotels.booking.hotel.repository.enums.City;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record HotelUpdateDto (
    String name,
    City city,
    String address,
    String website,
    String phone,
    String email,
    BigDecimal price,
    String description,
    boolean active
){
}
