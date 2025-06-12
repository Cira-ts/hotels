package com.hotels.booking.reservation.repository;

import com.hotels.booking.reservation.controller.dto.ReservationGetDatesDto;
import com.hotels.booking.reservation.repository.entity.Reservation;
import com.hotels.booking.reservation.repository.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserId(Long userId);


    List<ReservationGetDatesDto> findByRoomIdAndStatusIn(Long roomId, List<ReservationStatus> statuses);

    List<Reservation> findAllByStatus(ReservationStatus reservationStatus);
}
