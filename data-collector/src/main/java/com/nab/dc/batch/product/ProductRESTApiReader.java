package com.nab.dc.batch.product;

import com.nab.dc.dto.tiki.TikiProductDetailDto;
import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;
import org.springframework.batch.item.ItemReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ProductRESTApiReader<T> implements ItemReader<Map<PublisherProductMappingEntity, T>> {
    protected Fetcher<String, T> apiFetcher;
    protected Map<PublisherProductMappingEntity, T> detailDtoMap;
    protected PublisherProductMappingRepository publisherProductMappingRepository;

    protected ProductRESTApiReader(PublisherProductMappingRepository publisherProductMappingRepository, Fetcher<String, T> apiFetcher) {
        this.publisherProductMappingRepository = publisherProductMappingRepository;
        this.apiFetcher = apiFetcher;
    }

    @Override
    public Map<PublisherProductMappingEntity, T> read() throws Exception {
        Map<PublisherProductMappingEntity, String> productMap = getProductIdMap();
        if (detailDtoMap == null) {
            detailDtoMap = new HashMap<>();
            for (Map.Entry<PublisherProductMappingEntity, String> productEntry : productMap.entrySet()) {
                detailDtoMap.put(productEntry.getKey(), apiFetcher.fetch(productEntry.getValue()));
            }
            return detailDtoMap;
        }
        return null;
    }

    protected Map<PublisherProductMappingEntity, String> getProductIdMap() {
        return publisherProductMappingRepository.findAllByPublisherProductName(getPublisherName())
                .stream()
                .collect(Collectors.toMap(p -> p, PublisherProductMappingEntity::getPublisherProductId));
    }

    protected abstract PublisherName getPublisherName();
}
