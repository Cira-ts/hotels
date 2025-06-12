package com.hotels.booking.hotel.controller;

import com.hotels.booking.common.SortType;
import com.hotels.booking.hotel.controller.dto.HotelGetDto;
import com.hotels.booking.hotel.repository.enums.City;
import com.hotels.booking.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/app-user/hotels")
@RequiredArgsConstructor
public class AppUserHotelController {

    private final HotelService service;

    @GetMapping("/cities")
    @PreAuthorize("hasRole('USER')")
    public List<City> getCities() {
        return Arrays.asList(City.values());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<HotelGetDto> getHotels(@RequestParam(required = false, defaultValue= "ASC") SortType sortType,
                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size,
                                       @RequestParam(required = false, defaultValue = "name") String sortBy,
                                       @RequestParam(required = false) City city,
                                       @RequestParam(required = false) String search
    ) {
        return service.getHotels(sortType, page, size, sortBy, city, search);
    }

}
