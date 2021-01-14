package com.nab.dc.fetcher;

import com.nab.dc.dto.tiki.TikiProductDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TikiAPIFetcher implements Fetcher<String, TikiProductDetailDto> {

    private static final String URL_PATTERN = "http://localhost:9000/tiki/api/v1/product/%s";

    @Override
    public TikiProductDetailDto fetch(String requestId) {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = String.format(URL_PATTERN, requestId);
        ResponseEntity<TikiProductDetailDto> response = restTemplate.getForEntity(requestUrl, TikiProductDetailDto.class);
        return response.getBody();
    }
}
