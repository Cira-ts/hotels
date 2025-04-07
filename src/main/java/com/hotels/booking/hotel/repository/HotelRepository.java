package com.hotels.booking.hotel.repository;

import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.hotel.controller.dto.HotelGetDto;
import com.hotels.booking.hotel.repository.entity.Hotel;
import com.hotels.booking.hotel.repository.enums.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByActiveTrueOrderByPriceDesc();

    List<Hotel> findByActiveTrueOrderByPriceAsc();

    @Query("""
            Select
                h.name as name,
                h.city as city,
                h.website as website,
                h.active as active
            FROM Hotel h
            where h.active = true
                and (:city is null or h.city = :city)
                and (:search is null
                            or h.name ilike %:search%
                            or h.address ilike %:search%)
            """)
    List<HotelGetDto> getHotels(City city, String search);

    @Query("""
            SELECT
                h.id    as id,
                h.name  as name
            FROM Hotel h
            WHERE h.active = true
        """
    )
    List<IdNameDto> findHotelIdNames();
}


