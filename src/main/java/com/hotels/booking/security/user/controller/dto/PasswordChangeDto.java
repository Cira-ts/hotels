package com.hotels.booking.security.user.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PasswordChangeDto(
        @NotEmpty
        @Size(min = 6)
        String oldPassword,

        @NotEmpty
        @Size(min = 6)
        String newPassword
) {
}
