package com.hotels.booking.room.service;

import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.hotel.repository.HotelRepository;
import com.hotels.booking.hotel.repository.entity.Hotel;
import com.hotels.booking.hotel.service.HotelService;
import com.hotels.booking.room.controller.dto.RoomCreateDto;
import com.hotels.booking.room.controller.dto.RoomDetailsGetDto;
import com.hotels.booking.room.controller.dto.RoomGetDto;
import com.hotels.booking.room.repository.RoomRepository;
import com.hotels.booking.room.repository.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final HotelService hotelService;

    private Room lookUpRoom(Long id) {
        return repository.findById(id).orElseThrow(()->new IllegalArgumentException("Room not found"));
    }

    public List<IdNameDto> getHotelsIdName(){
        return hotelService.getHotelsIdName();
    }

    private Room toRoom(RoomCreateDto dto) {
        Hotel hotel = hotelService.getHotelById(dto.hotelId());
        return Room.builder()
                .number(dto.number())
                .price(dto.price())
                .type(dto.type())
                .balcony(dto.balcony())
                .active(dto.active())
                .hotel(hotel)
                .build();
    }

    public List<RoomGetDto> getRooms(Long hotelId) {
        return repository.findRoomsByHotel_id(hotelId);
    }

    public RoomDetailsGetDto getRoomById(Long roomId) {
        return repository.getRoomDetailsById(roomId);
    }

    public void createRoom(RoomCreateDto dto) {
        repository.save(toRoom(dto));
    }

    public void updateStatus(Long id, boolean active) {
        Room room = lookUpRoom(id);
        room.setActive(active);
        repository.save(room);
    }

    public void deleteRoom(Long id) {
        repository.deleteById(id);
    }
}
