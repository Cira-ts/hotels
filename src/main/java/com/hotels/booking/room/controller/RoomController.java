package com.hotels.booking.room.controller;

import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.common.SortType;
import com.hotels.booking.room.controller.dto.RoomCreateDto;
import com.hotels.booking.room.controller.dto.RoomDetailsGetDto;
import com.hotels.booking.room.controller.dto.RoomGetDto;
import com.hotels.booking.room.repository.enums.RoomType;
import com.hotels.booking.room.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping("hotels")
    public List<IdNameDto> getHotels(){
        return service.getHotelsIdName();
    }

    @GetMapping("/types")
    public List<RoomType> getTypes(){
        return Arrays.asList(RoomType.values());
    }

    @GetMapping()
    public Page<RoomGetDto> getRooms(@RequestParam Long hotelId,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size,
                                     @RequestParam(defaultValue = "number", required = false) String sortByField,
                                     @RequestParam(defaultValue = "ASC", required = false) SortType sortType) {
        return service.getRooms(hotelId, page, size, sortByField,sortType);
    }

    @GetMapping("/{id}")
    public RoomDetailsGetDto getRoomById(@PathVariable Long id) {
        return service.getRoomById(id);
    }

    @PostMapping
    public void createRoom(@RequestBody @Valid RoomCreateDto dto) {
        service.createRoom(dto);
    }

    @PatchMapping("/{id}/change-status")
    public void updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        service.updateStatus(id, active);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        service.deleteRoom(id);
    }

}
