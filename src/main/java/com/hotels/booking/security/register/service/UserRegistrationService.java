package com.hotels.booking.security.register.service;

import com.hotels.booking.common.AppConstants;
import com.hotels.booking.exception.BusinessException;
import com.hotels.booking.exception.ExceptionUtil;
import com.hotels.booking.security.auth.service.AuthenticationService;
import com.hotels.booking.security.register.controller.dto.RegisterUserRequestDto;
import com.hotels.booking.security.service.JwtService;
import com.hotels.booking.security.user.repository.AppUserRepository;
import com.hotels.booking.security.user.repository.entity.AppUser;
import com.hotels.booking.security.user.repository.enums.UserStatus;
import com.nulabinc.zxcvbn.Zxcvbn;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationService {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository repository;

    public void registerUser(RegisterUserRequestDto request) {
        if (new Zxcvbn().measure(request.password()).getScore() < AppConstants.PASSWORD_STRENGTH) {
            throw new BusinessException("weak_password");
        }

        AppUser user = AppUser.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .country(request.country())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .status(UserStatus.ACTIVE)
                .registrationDate(LocalDateTime.now())
                .role(request.role())  //
                .build();
        saveUserAndGenerateResponse(user);
    }

    private void saveUserAndGenerateResponse(AppUser user) {
        try {
            repository.saveAndFlush(user);
        } catch (Exception e) {
            ExceptionUtil.handleDatabaseExceptions(e, Map.of("unique_email", "email_already_exists"));
        }
        String jwtToken = jwtService.generateToken(user); //
        authenticationService.saveUserToken(user, jwtToken);
    }
}
