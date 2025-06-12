package com.hotels.booking.room.controller.dto;

import com.hotels.booking.room.repository.enums.RoomType;

import java.math.BigDecimal;

public interface RoomDetailsGetDto {
    Long getId();
    Integer getNumber();
    RoomType getType();
    BigDecimal getPrice();
    boolean getBalcony();
    boolean getActive();
}
