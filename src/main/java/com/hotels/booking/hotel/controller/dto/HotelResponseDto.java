package com.hotels.booking.hotel.controller.dto;

import com.hotels.booking.hotel.repository.enums.City;
import lombok.Builder;

@Builder
public record HotelResponseDto  (
        String name,
        City city,
        String website,
        boolean active
)
{
}
