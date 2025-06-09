package com.hotels.booking.security.register.controller.dto;

import com.hotels.booking.common.AppConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterUserRequestDto(
        @NotEmpty
        String firstName,

        @NotEmpty
        String lastName,

        @NotEmpty
        String country,

        @NotEmpty
        @Pattern(regexp = AppConstants.EMAIL_PATTERN)
        String email,

        @NotEmpty
        @Size(min = 6)
        String password
) {
}
