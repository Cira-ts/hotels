package com.hotels.booking.room.controller.dto;

import java.math.BigDecimal;

public interface RoomDetailsGetDto {
    Integer getNumber();
    Integer getType();
    BigDecimal getPrice();
    boolean getBalcony();
    boolean getActive();
    String getHotelName();
}
