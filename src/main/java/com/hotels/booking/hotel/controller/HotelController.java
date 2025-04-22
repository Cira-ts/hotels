package com.hotels.booking.hotel.controller;

import com.hotels.booking.hotel.controller.dto.HotelCreateDto;
import com.hotels.booking.hotel.controller.dto.HotelGetDto;
import com.hotels.booking.hotel.controller.dto.HotelResponseDto;
import com.hotels.booking.hotel.controller.dto.HotelUpdateDto;
import com.hotels.booking.hotel.repository.HotelRepository;
import com.hotels.booking.hotel.repository.enums.City;
import com.hotels.booking.common.SortType;
import com.hotels.booking.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;
    private final HotelRepository hotelRepository;

    @GetMapping("/cities")
    public List<City> getCities() {
        return Arrays.asList(City.values());
    }

    @GetMapping
    public Page<HotelGetDto> getHotels(@RequestParam(required = false, defaultValue= "ASC") SortType sortType,
                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size,
                                       @RequestParam(required = false, defaultValue = "name") String sortBy,
                                       @RequestParam(required = false) City city,
                                       @RequestParam(required = false) String search
                                       ) {
        return service.getHotels(sortType, page, size, sortBy, city, search);
    }

    @PostMapping
    public HotelResponseDto createHotel(@RequestBody HotelCreateDto dto) {
        return service.saveHotel(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id) {
        service.deleteHotel(id);
    }

    @PutMapping("/{id}")
    public HotelResponseDto updateHotel(@PathVariable Long id, @RequestBody HotelUpdateDto dto){
        return service.updateHotel(id, dto);
    }

}
