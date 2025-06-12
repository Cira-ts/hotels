package com.hotels.booking.security.register.controller;

import com.hotels.booking.exception.SecurityViolationException;
import com.hotels.booking.security.register.controller.dto.RegisterUserRequestDto;
import com.hotels.booking.security.register.service.UserRegistrationService;
import com.hotels.booking.security.user.repository.enums.Role;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/registration"})@RequiredArgsConstructor
public class AdminUserRegistrationController {
    private final UserRegistrationService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterUserRequestDto request) {
        service.registerUser(request);
    }

}
