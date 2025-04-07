package com.hotels.booking.hotel.service;


import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.hotel.controller.dto.HotelCreateDto;
import com.hotels.booking.hotel.controller.dto.HotelGetDto;
import com.hotels.booking.hotel.controller.dto.HotelUpdateDto;
import com.hotels.booking.hotel.repository.enums.City;
import com.hotels.booking.hotel.repository.enums.SortType;
import com.hotels.booking.hotel.repository.HotelRepository;
import com.hotels.booking.hotel.controller.dto.HotelResponseDto;
import com.hotels.booking.hotel.repository.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<IdNameDto> getHotelsIdName() {
        return hotelRepository.findHotelIdNames();
    }

    public HotelResponseDto toHotelResponseDto(Hotel hotel) {
        return HotelResponseDto.builder()
                .name(hotel.getName())
                .city(hotel.getCity())
                .website(hotel.getWebsite())
                .active(hotel.isActive())
                .build();
    }

    public Hotel toHotel(HotelCreateDto hotelDto) {
        return Hotel.builder()
                .name(hotelDto.name())
                .city(hotelDto.city())
                .phone(hotelDto.phone())
                .email(hotelDto.email())
                .website(hotelDto.website())
                .active(hotelDto.active())
                .build();
    }

    public List<HotelGetDto> getHotels(City city, String search) {
        return hotelRepository.getHotels(city, search);
    }

    public HotelResponseDto saveHotel(HotelCreateDto dto) {
        Hotel hotel = toHotel(dto);
        hotelRepository.save(hotel);
        return toHotelResponseDto(hotel);
    }

    public void deleteHotel(long hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    public Hotel getHotelById(long hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    public HotelResponseDto updateHotel(long id, HotelUpdateDto dto){
        Hotel hotelToUpdate = hotelRepository.findById(id).orElse(new Hotel());
        hotelToUpdate.setName(dto.name());
        hotelToUpdate.setCity(dto.city());
        hotelToUpdate.setAddress(dto.address());
        hotelToUpdate.setPhone(dto.phone());
        hotelToUpdate.setEmail(dto.email());
        hotelToUpdate.setWebsite(dto.website());
        hotelToUpdate.setActive(dto.active());
        hotelToUpdate.setPrice(dto.price());
        hotelRepository.save(hotelToUpdate);
        return toHotelResponseDto(hotelToUpdate);
    }

    public List<HotelResponseDto> getSortedHotelsByPrice(SortType sortType) {
        List<Hotel> hotels;
        if (SortType.DESC.equals(sortType)) {
            hotels = hotelRepository.findByActiveTrueOrderByPriceDesc();
        }
        else{
            hotels = hotelRepository.findByActiveTrueOrderByPriceAsc();
        }
        return hotels.stream()
                .map(this::toHotelResponseDto)
                .collect(Collectors.toList());
    }

}
