package com.hotels.booking.room.repository.entity;


import com.hotels.booking.hotel.repository.entity.Hotel;
import com.hotels.booking.reservation.repository.entity.Reservation;
import com.hotels.booking.room.repository.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_room")
    @SequenceGenerator(name = "seq_room", sequenceName = "seq_room", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(name="number")
    private int number;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="balcony")
    private boolean balcony;

    @Column(name="active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;

    @OneToMany(mappedBy="room")
    private List<Reservation> reservations;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return type == room.type && number == room.number && balcony == room.balcony && active == room.active && Objects.equals(id, room.id) && Objects.equals(price, room.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, number, price, balcony, active);
    }
}
