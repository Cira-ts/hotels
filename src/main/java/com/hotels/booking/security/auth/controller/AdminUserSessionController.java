package com.hotels.booking.security.auth.controller;

import com.hotels.booking.security.auth.controller.dto.AuthenticationRequestDto;
import com.hotels.booking.security.auth.controller.dto.AuthenticationResponse;
import com.hotels.booking.security.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("admin-user/session")
@RequiredArgsConstructor
public class AdminUserSessionController {
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequestDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }
}
