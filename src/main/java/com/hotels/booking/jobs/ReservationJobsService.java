package com.hotels.booking.jobs;

import com.hotels.booking.reservation.repository.ReservationRepository;
import com.hotels.booking.reservation.repository.entity.Reservation;
import com.hotels.booking.reservation.repository.enums.ReservationStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationJobsService {

    private final ReservationRepository reservationRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateStatusToActive() {
        List<Reservation> reservations = reservationRepository.findAllByStatus(ReservationStatus.PLANNED);
        reservations.forEach(r -> {
            if (!LocalDate.now().isBefore(r.getCheckInDate())) {
                r.setStatus(ReservationStatus.ACTIVE);
            }
        });
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void updateStatusToCompleted() {
        List<Reservation> reservations = reservationRepository.findAllByStatus(ReservationStatus.ACTIVE);
        reservations.forEach(r -> {
            if (LocalDate.now().isAfter(r.getCheckOutDate())) {
                r.setStatus(ReservationStatus.COMPLETED);
            }
        });
    }

}
