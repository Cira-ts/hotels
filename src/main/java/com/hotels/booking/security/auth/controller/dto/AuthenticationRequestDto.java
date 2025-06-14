package com.hotels.booking.security.auth.controller.dto;

import com.hotels.booking.common.AppConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AuthenticationRequestDto(
        @NotEmpty
        @Pattern(regexp = AppConstants.EMAIL_PATTERN)
        String email,

        @NotEmpty
        String password
) {
}
