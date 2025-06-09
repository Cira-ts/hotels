package com.hotels.booking.security.user.repository;

import com.hotels.booking.security.user.repository.entity.AppUser;
import com.hotels.booking.security.user.repository.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmailAndStatus(String email, UserStatus userStatus);

    Optional<AppUser> findByIdAndStatus(Long id, UserStatus userStatus);

    AppUser findByEmail(String username);
}
