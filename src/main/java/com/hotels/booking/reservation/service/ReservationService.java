package com.hotels.booking.reservation.service;

import com.hotels.booking.exception.SecurityViolationException;
import com.hotels.booking.reservation.controller.dto.ReservationCreateDto;
import com.hotels.booking.reservation.controller.dto.ReservationGetDatesDto;
import com.hotels.booking.reservation.controller.dto.ReservationResponseDto;
import com.hotels.booking.reservation.controller.dto.ReservationUpdateDto;
import com.hotels.booking.reservation.repository.ReservationRepository;
import com.hotels.booking.reservation.repository.entity.Reservation;
import com.hotels.booking.reservation.repository.enums.ReservationStatus;
import com.hotels.booking.room.repository.entity.Room;
import com.hotels.booking.room.service.RoomService;
import com.hotels.booking.security.user.repository.entity.AppUser;
import com.hotels.booking.security.user.service.AppUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;
    private final RoomService roomService;
    private final AppUserService appUserService;

    private Reservation lookUpReservation(Long id){
        return repository.findById(id).orElse(null);
    }

    private Reservation toReservation(ReservationCreateDto dto) {
        Room room = roomService.lookUpRoom(dto.roomId());
        return Reservation.builder()
                .room(room)
                .checkInDate(dto.checkInDate())
                .checkOutDate(dto.checkOutDate())
                .bookingTime(LocalDateTime.now())
                .user(appUserService.currentUser())
                .status(ReservationStatus.PLANNED)
                .build();
    }

    private BigDecimal calculateTotalPrice(Reservation reservation) {
        return null;
    }

    private ReservationResponseDto toResponseDto(Reservation reservation){
        return ReservationResponseDto.builder()
                .roomId(reservation.getRoom().getId())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .status(reservation.getStatus())
                .totalPrice(calculateTotalPrice(reservation))
                .build();
    }

    private List<Reservation> getReservationsByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<ReservationResponseDto> getAllReservationsByUser() {
        AppUser currentUser = appUserService.currentUser();
        return repository.findAllByUserId(currentUser.getId())
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ReservationGetDatesDto> getRoomReservedDates(Long roomId){
        return repository.findByRoomIdAndStatusIn(roomId, List.of(ReservationStatus.PLANNED, ReservationStatus.ACTIVE));
    }

    public void makeReservation(ReservationCreateDto dto) {
        repository.save(toReservation(dto));
    }

    public void cancelReservation(Long id) {
        Reservation reservation = lookUpReservation(id);
        validateUser(reservation.getUser().getId());
        reservation.setStatus(ReservationStatus.CANCELLED);
    }

    public void updateReservation(Long id, ReservationUpdateDto dto){
        Reservation reservation = lookUpReservation(id);

        validateUser(reservation.getUser().getId());

        reservation.setCheckInDate(dto.checkInDate());
        reservation.setCheckOutDate(dto.checkOutDate());
    }

    private void validateUser(Long userId) {
        if(!Objects.equals(appUserService.currentUser().getId(), userId))
            throw new SecurityViolationException();
    }

}
