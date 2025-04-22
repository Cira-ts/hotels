package com.hotels.booking.common;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public class PageRequestGetter {


    public static PageRequest getPageable(Integer page,
                                          Integer size,
                                          SortType sortType,
                                          String sortByField) {
        return PageRequest.of(page,
                size,
                sortType.equals(SortType.DESC) ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortByField);
    }
}
