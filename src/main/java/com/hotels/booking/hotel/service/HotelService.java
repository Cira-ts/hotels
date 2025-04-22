package com.hotels.booking.hotel.service;


import com.hotels.booking.common.IdNameDto;
import com.hotels.booking.exceptions.EntityNotFoundException;
import com.hotels.booking.hotel.controller.dto.HotelCreateDto;
import com.hotels.booking.hotel.controller.dto.HotelGetDto;
import com.hotels.booking.hotel.controller.dto.HotelUpdateDto;
import com.hotels.booking.hotel.repository.enums.City;
import com.hotels.booking.common.SortType;
import com.hotels.booking.hotel.repository.HotelRepository;
import com.hotels.booking.hotel.controller.dto.HotelResponseDto;
import com.hotels.booking.hotel.repository.entity.Hotel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<IdNameDto> getHotelsIdName() {
        return hotelRepository.findHotelIdNames();
    }

    public Hotel lookUpHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow( () ->new EntityNotFoundException("Hotel not found, incorrect id")) ;
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

    public Page<HotelGetDto> getHotels(SortType sortType, Integer page, Integer size, String sortBy, City city, String search) {
        Pageable pageable = PageRequest.of(page, size, sortType.equals(SortType.ASC) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        return hotelRepository.getHotels(city, search, pageable);
    }

    public HotelResponseDto saveHotel(HotelCreateDto dto) {
        Hotel hotel = toHotel(dto);
        hotelRepository.save(hotel);
        return toHotelResponseDto(hotel);
    }

    public void deleteHotel(long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new EntityNotFoundException("Hotel with id " + hotelId + " not found");
        }  // keep?
        hotelRepository.deleteById(hotelId);
    }

    public Hotel getHotelById(long hotelId) {
        return lookUpHotelById(hotelId);
    }

    public HotelResponseDto updateHotel(long id, HotelUpdateDto dto){
        Hotel hotelToUpdate = lookUpHotelById(id);
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
