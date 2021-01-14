package com.nab.api.product.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {

    private PageableUtil() {

    }

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_INDEX = 1;
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_ORDER_BY= "DESC";

    public static Pageable getPageable(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 0;
        }
        if (pageSize == null) {
            pageSize = 0;
        }
        pageIndex = pageIndex > 0 ? pageIndex - 1 : DEFAULT_PAGE_INDEX - 1;
        pageSize  = pageSize > 0  ? pageSize : DEFAULT_PAGE_SIZE;
        return PageRequest.of(pageIndex, pageSize, Sort.by(DEFAULT_SORT_BY).descending());
    }

    public static Pageable getPageable(int pageIndex, int pageSize, String sortBy, String orderBy) {
        pageIndex = pageIndex > 0 ? pageIndex - 1 : DEFAULT_PAGE_INDEX - 1;
        pageSize  = pageSize > 0  ? pageSize : DEFAULT_PAGE_SIZE;

        return DEFAULT_ORDER_BY.equalsIgnoreCase(orderBy)
                ? PageRequest.of(pageIndex, pageSize, Sort.by(sortBy).descending())
                : PageRequest.of(pageIndex, pageSize, Sort.by(sortBy).ascending());
    }
}
