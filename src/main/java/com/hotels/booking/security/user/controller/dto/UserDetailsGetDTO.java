package com.hotels.booking.security.user.controller.dto;

import lombok.Builder;

@Builder
public record UserDetailsGetDTO(
        String firstName,
        String lastName,
        String country,
        String email
) {
}
