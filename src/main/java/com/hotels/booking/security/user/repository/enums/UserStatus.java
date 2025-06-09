package com.hotels.booking.security.user.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ACTIVE("აქტიური"),
    DISABLED("დაბლოკილი");

    private final String nameKa;
}
