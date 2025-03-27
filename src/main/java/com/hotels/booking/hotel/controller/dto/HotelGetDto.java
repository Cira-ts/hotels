package com.hotels.booking.hotel.controller.dto;

import com.hotels.booking.hotel.repository.enums.City;

public interface HotelGetDto {
    String getName();
    City getCity();
    String getWebsite();
    boolean getActive();
}
