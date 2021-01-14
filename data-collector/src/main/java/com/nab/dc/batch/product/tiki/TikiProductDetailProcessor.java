package com.nab.dc.batch.product.tiki;

import com.nab.dc.batch.product.ProductRESTApiProcessor;
import com.nab.dc.dto.tiki.TikiProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.repository.PublisherProductMappingRepository;
import com.nab.dc.repository.PublisherRepository;

public class TikiProductDetailProcessor extends ProductRESTApiProcessor<TikiProductDetailDto, PublisherProductDetailEntity> {

    @Override
    public PublisherProductDetailEntity fromEntity(TikiProductDetailDto tikiProductDetailDto, PublisherProductMappingEntity publisherProductMappingEntity) {
        PublisherProductDetailEntity publisherProductDetailEntity = new PublisherProductDetailEntity();
        publisherProductDetailEntity.setPublisherProductId(tikiProductDetailDto.getProductId());
        publisherProductDetailEntity.setPublisherProductMappingEntity(publisherProductMappingEntity);
        publisherProductDetailEntity.setPublisherProductName(PublisherName.TIKI);
        publisherProductDetailEntity.setPrice(tikiProductDetailDto.getPrice());
        publisherProductDetailEntity.setDiscountRate(tikiProductDetailDto.getDealSpecs().getDiscountRate());
        publisherProductDetailEntity.setImgURL(tikiProductDetailDto.getImgUrl());
        return publisherProductDetailEntity;
    }
}
