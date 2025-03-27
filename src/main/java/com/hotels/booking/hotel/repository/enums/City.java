package com.hotels.booking.hotel.repository.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum City {
    LONDON("ლონდონი"),
    ROME("რომი"),
    BERLIN("ბერლინი"),
    ATHENS("ათენი"),
    BUDAPEST("ბუდაპეშტი"),
    AMSTERDAM("ამსტერდამი"),
    VIENNA("ვენა");

    private final String nameKa;

    public String getCode() {
        return this.name();
    }
}
