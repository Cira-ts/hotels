package com.hotels.booking.room.controller.dto;

import com.hotels.booking.room.repository.enums.RoomType;
import lombok.Builder;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;


@Builder
public record RoomCreateDto (
    @NotNull
    Long hotelId,

    @NotNull
    Integer number,

    @NotNull
    RoomType type,

    @NotNull
    BigDecimal price,

    @NotNull
    boolean balcony,

    @NotNull
    boolean active
){
}
