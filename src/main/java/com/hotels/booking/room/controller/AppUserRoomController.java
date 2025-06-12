package com.hotels.booking.room.controller;


import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.common.SortType;
import com.hotels.booking.room.controller.dto.RoomDetailsGetDto;
import com.hotels.booking.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app-user/rooms")
@RequiredArgsConstructor
public class AppUserRoomController {

    private final RoomService service;

    @GetMapping("hotels")
    @PreAuthorize("hasRole('USER')")
    public List<IdNameDto> getHotels(){
        return service.getHotelsIdName();
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public Page<RoomDetailsGetDto> getRooms(@RequestParam Long hotelId,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "number", required = false) String sortByField,
                                            @RequestParam(defaultValue = "ASC", required = false) SortType sortType) {
        return service.getRooms(hotelId, page, size, sortByField,sortType);
    }

}
