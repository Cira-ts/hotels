package com.hotels.booking.reservation.repository.entity;

import com.hotels.booking.reservation.repository.enums.ReservationStatus;
import com.hotels.booking.room.repository.entity.Room;
import com.hotels.booking.security.user.repository.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reservation")
    @SequenceGenerator(name = "seq_reservation", sequenceName = "seq_reservation", allocationSize = 1, initialValue = 1000)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;

    @Column(name="booking_time")
    private LocalDateTime bookingTime;

    @Column(name="check_in_date")
    private LocalDate checkInDate;

    @Column(name="check_out_date")
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;
}
