package com.nab.dc.batch.product.shopee;

import com.nab.dc.batch.product.ProductRESTApiReader;
import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;

public class ShopeeApiReader extends ProductRESTApiReader<ShopeeProductDetailDto> {

    public ShopeeApiReader(PublisherProductMappingRepository publisherProductMappingRepository, Fetcher<String, ShopeeProductDetailDto> apiFetcher) {
        super(publisherProductMappingRepository, apiFetcher);
    }

    @Override
    protected PublisherName getPublisherName() {
        return PublisherName.SHOPEE;
    }
}
