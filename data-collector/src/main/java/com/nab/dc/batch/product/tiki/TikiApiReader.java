package com.nab.dc.batch.product.tiki;

import com.nab.dc.batch.product.ProductRESTApiReader;
import com.nab.dc.dto.tiki.TikiProductDetailDto;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;

public class TikiApiReader extends ProductRESTApiReader<TikiProductDetailDto> {

    public TikiApiReader(PublisherProductMappingRepository publisherProductMappingRepository, Fetcher<String, TikiProductDetailDto> apiFetcher) {
        super(publisherProductMappingRepository, apiFetcher);
    }

    @Override
    protected PublisherName getPublisherName() {
        return PublisherName.TIKI;
    }
}
