package com.hotels.booking.room.service;

import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.common.PageRequestGetter;
import com.hotels.booking.common.SortType;
import com.hotels.booking.exceptions.BadRequestException;
import com.hotels.booking.exceptions.EntityNotFoundException;
import com.hotels.booking.hotel.repository.entity.Hotel;
import com.hotels.booking.hotel.service.HotelService;
import com.hotels.booking.room.controller.dto.RoomCreateDto;
import com.hotels.booking.room.controller.dto.RoomDetailsGetDto;
import com.hotels.booking.room.controller.dto.RoomGetDto;
import com.hotels.booking.room.repository.RoomRepository;
import com.hotels.booking.room.repository.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final HotelService hotelService;

    private Room lookUpRoom(Long id) {
        return repository.findById(id).orElseThrow(()->new EntityNotFoundException("Room not found, incorrect id"));
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

    public Page<RoomGetDto> getRooms(Long hotelId, Integer page, Integer size, String sortByField, SortType sortType) {
        Pageable pageable = PageRequestGetter.getPageable(page,size,sortType,sortByField);
        return repository.findRoomsByHotel_id(hotelId, pageable);
    }

    public RoomDetailsGetDto getRoomById(Long roomId) {
        RoomDetailsGetDto room = repository.getRoomDetailsById(roomId);
        if (room == null) {
            throw new EntityNotFoundException("Room with id " + roomId + " not found");
        }
        return room;
    }

    public void createRoom(RoomCreateDto dto) {
        try {
            Room room = toRoom(dto);
            repository.save(room);
        } catch (Exception ex) {
            throw new BadRequestException("Room creation failed, error: " + ex.getMessage());
        }
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
