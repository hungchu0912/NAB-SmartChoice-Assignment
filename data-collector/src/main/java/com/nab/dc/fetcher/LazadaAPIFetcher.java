package com.nab.dc.fetcher;

import com.nab.dc.dto.lazada.LazadaProductDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LazadaAPIFetcher implements Fetcher<String, LazadaProductDetailDto> {

    private static final String URL_PATTERN = "http://localhost:9000/lazada/api/v1/item/%s";

    @Override
    public LazadaProductDetailDto fetch(String requestId) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = String.format(URL_PATTERN, requestId);
        ResponseEntity<LazadaProductDetailDto> response = restTemplate.getForEntity(requestUrl, LazadaProductDetailDto.class);
        return response.getBody();
    }
}
