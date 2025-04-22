package com.hotels.booking.room.repository;

import com.hotels.booking.hotel.controller.dto.HotelGetDto;
import com.hotels.booking.room.controller.dto.RoomDetailsGetDto;
import com.hotels.booking.room.controller.dto.RoomGetDto;
import com.hotels.booking.room.repository.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<RoomGetDto> findRoomsByHotel_id(Long hotelId, Pageable pageable);

    @Query("""
        SELECT
            r.number    as number,
            r.type      as type,
            r.price     as price,
            r.balcony   as balcony,
            r.active    as active,
            rh.name     as hotelName
        from Room r
        join r.hotel rh
        where r.id = :id
""")
    RoomDetailsGetDto getRoomDetailsById(Long id);

}


