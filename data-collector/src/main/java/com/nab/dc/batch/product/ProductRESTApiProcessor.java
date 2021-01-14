package com.nab.dc.batch.product;

import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.repository.PublisherProductMappingRepository;
import com.nab.dc.repository.PublisherRepository;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ProductRESTApiProcessor <I, O> implements ItemProcessor<Map<PublisherProductMappingEntity, I>, List<O>> {

    @Override
    public List<O> process(Map<PublisherProductMappingEntity, I> productDetailMap) throws Exception {
        return productDetailMap.entrySet().stream().map(e -> this.fromEntity(e.getValue(), e.getKey())).collect(Collectors.toList());
    }

    public abstract O fromEntity(I productDetailDto, PublisherProductMappingEntity publisherProductMappingEntity);
}
