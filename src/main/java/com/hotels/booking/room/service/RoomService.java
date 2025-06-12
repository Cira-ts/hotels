package com.hotels.booking.room.service;

import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.common.PageRequestGetter;
import com.hotels.booking.common.SortType;
import com.hotels.booking.exception.ExceptionUtil;
import com.hotels.booking.exception.SecurityViolationException;
import com.hotels.booking.hotel.repository.entity.Hotel;
import com.hotels.booking.hotel.service.HotelService;
import com.hotels.booking.room.controller.dto.RoomCreateDto;
import com.hotels.booking.room.controller.dto.RoomDetailsGetDto;
import com.hotels.booking.room.repository.RoomRepository;
import com.hotels.booking.room.repository.entity.Room;
import com.hotels.booking.security.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final HotelService hotelService;
    private final AppUserService userService;

    public Room lookUpRoom(Long id) {
        return repository.findById(id).orElseThrow(SecurityViolationException::new);
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

    public Page<RoomDetailsGetDto> getRooms(Long hotelId, Integer page, Integer size, String sortByField, SortType sortType) {
        Pageable pageable = PageRequestGetter.getPageable(page,size,sortType,sortByField);
        return repository.findRoomsByHotel_id(hotelId, pageable);
    }

    public RoomDetailsGetDto getRoomById(Long roomId) {
        RoomDetailsGetDto room = repository.getRoomDetailsById(roomId);
        if (room == null) {
            throw new SecurityViolationException();
        }
        return room;
    }

    public void createRoom(RoomCreateDto dto) {
        try {
            Room room = toRoom(dto);

            validateAdmin(room.getHotel().getAuthor().getId());

            repository.saveAndFlush(room);
        } catch (Exception e) {
            ExceptionUtil.handleDatabaseExceptions(e, Map.of("un_number_hotel_id", "hotel_cant_have_two_rooms_with_same_number"));
        }
    }

    public void updateStatus(Long id, boolean active) {
        Room room = lookUpRoom(id);

        validateAdmin(room.getHotel().getAuthor().getId());

        room.setActive(active);
        repository.save(room);
    }

    public void deleteRoom(Long id) {
        Room room = lookUpRoom(id);
        validateAdmin(room.getHotel().getAuthor().getId());
        repository.deleteById(id);
    }

    private void validateAdmin(Long userId) {
        if(!Objects.equals(userService.currentUser().getId(), userId))
            throw new SecurityViolationException();
    }
}
