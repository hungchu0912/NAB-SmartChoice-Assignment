package com.nab.dc.fetcher;

import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ShopeeAPIFetcher implements Fetcher<String, ShopeeProductDetailDto> {

    private static final String URL_PATTERN = "http://localhost:9000/shopee/api/v1/item/%s";

    @Override
    public ShopeeProductDetailDto fetch(String requestId) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = String.format(URL_PATTERN, requestId);
        ResponseEntity<ShopeeProductDetailDto> response = restTemplate.getForEntity(requestUrl, ShopeeProductDetailDto.class);
        return response.getBody();
    }
}
