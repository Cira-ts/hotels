package com.hotels.booking.security.user.controller;

import com.hotels.booking.security.user.controller.dto.PasswordChangeDto;
import com.hotels.booking.security.user.controller.dto.ProfileUpdateDto;
import com.hotels.booking.security.user.controller.dto.UserDetailsGetDTO;
import com.hotels.booking.security.user.service.AppUserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-profile")
@RequiredArgsConstructor
public class AppUserProfileController {

    private final AppUserService service;

    @GetMapping
    @PermitAll
    public UserDetailsGetDTO getUserProfile() {
        return service.getProfile();
    }

    @PutMapping
    @PermitAll
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserProfile(@RequestBody @Valid ProfileUpdateDto dto) {
        service.updateUserProfile(dto);
    }

    @PatchMapping("change-password")
    @PermitAll
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody @Valid PasswordChangeDto dto) {
        service.changePassword(dto);
    }
}
