package com.hotels.booking.security.user.service;

import com.hotels.booking.common.AppConstants;
import com.hotels.booking.exception.BusinessException;
import com.hotels.booking.exception.SecurityViolationException;
import com.hotels.booking.security.user.controller.dto.PasswordChangeDto;
import com.hotels.booking.security.user.controller.dto.ProfileUpdateDto;
import com.hotels.booking.security.user.controller.dto.UserDetailsGetDTO;
import com.hotels.booking.security.user.repository.AppUserRepository;
import com.hotels.booking.security.user.repository.entity.AppUser;
import com.hotels.booking.security.user.repository.enums.UserStatus;
import com.nulabinc.zxcvbn.Zxcvbn;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;

    public AppUser lookUpActiveUserByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
                .orElseThrow(SecurityViolationException::new);
    }

    public AppUser lookUpActiveUserById(Long id) {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(SecurityViolationException::new);
    }

    public UserDetailsGetDTO getProfile() {
        AppUser user = currentUser();
        return UserDetailsGetDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .country(user.getCountry())
                .email(user.getEmail())
                .build();
    }

    public void updateUserProfile(ProfileUpdateDto dto) {
        AppUser user = currentUser();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setCountry(dto.country());
    }

    public void changePassword(PasswordChangeDto dto) {
        AppUser user = currentUser();
        if (!passwordEncoder.matches(dto.oldPassword(), user.getPassword())) {
            throw new BusinessException("incorrect_old_password");
        }

        if (new Zxcvbn().measure(dto.newPassword()).getScore() < AppConstants.PASSWORD_STRENGTH) {
            throw new BusinessException("weak_password");
        }

        user.setPassword(passwordEncoder.encode(dto.newPassword()));
    }

    public AppUser currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((AppUser) authentication.getPrincipal()).getUsername();
        return lookUpActiveUserByEmail(username);
    }
}
