package com.hotels.booking.room.controller.dto;

import com.hotels.booking.hotel.repository.entity.Hotel;

import java.math.BigDecimal;

public interface RoomGetDto {
    Long getId();
    int getNumber();
    boolean getActive();
}
