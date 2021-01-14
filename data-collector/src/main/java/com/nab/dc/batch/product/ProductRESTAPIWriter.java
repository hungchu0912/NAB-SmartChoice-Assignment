package com.nab.dc.batch.product;

import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.repository.PublisherProductDetailRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRESTAPIWriter implements ItemWriter<List<PublisherProductDetailEntity>> {

    @Autowired
    private PublisherProductDetailRepository publisherProductDetailRepository;

    @Override
    public void write(List<? extends List<PublisherProductDetailEntity>> productDetails) {
        productDetails.forEach(s -> publisherProductDetailRepository.saveAll(s));
    }
}
