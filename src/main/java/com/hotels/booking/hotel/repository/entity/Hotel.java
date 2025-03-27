package com.hotels.booking.hotel.repository.entity;

import com.hotels.booking.hotel.repository.enums.City;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="hotel")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hotel")
    @SequenceGenerator(name = "seq_hotel", sequenceName = "seq_hotel", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="city")
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="website")
    private String website;

    @Column(name="active")
    private boolean active;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hotel hotel)) return false;
        return active == hotel.active && Objects.equals(id, hotel.id)
                && Objects.equals(name, hotel.name)
                && Objects.equals(city, hotel.city)
                && Objects.equals(address, hotel.address)
                && Objects.equals(phone, hotel.phone)
                && Objects.equals(email, hotel.email)
                && Objects.equals(website, hotel.website)
                && Objects.equals(description, hotel.description)
                && Objects.equals(price, hotel.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, address, phone, email, website, active, description, price);
    }
}
