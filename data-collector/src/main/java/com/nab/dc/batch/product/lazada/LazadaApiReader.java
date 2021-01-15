package com.nab.dc.batch.product.lazada;

import com.nab.dc.batch.product.ProductRESTApiReader;
import com.nab.dc.dto.lazada.LazadaProductDetailDto;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;

public class LazadaApiReader extends ProductRESTApiReader<LazadaProductDetailDto> {

    public LazadaApiReader(PublisherProductMappingRepository publisherProductMappingRepository, Fetcher<String, LazadaProductDetailDto> apiFetcher) {
        super(publisherProductMappingRepository, apiFetcher);
    }

    @Override
    protected PublisherName getPublisherName() {
        return PublisherName.LAZADA;
    }
}
