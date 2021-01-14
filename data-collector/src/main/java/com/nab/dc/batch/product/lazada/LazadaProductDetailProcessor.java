package com.nab.dc.batch.product.lazada;

import com.nab.dc.batch.product.ProductRESTApiProcessor;
import com.nab.dc.dto.lazada.LazadaProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.repository.PublisherProductMappingRepository;
import com.nab.dc.repository.PublisherRepository;

public class LazadaProductDetailProcessor extends ProductRESTApiProcessor<LazadaProductDetailDto, PublisherProductDetailEntity> {

    @Override
    public PublisherProductDetailEntity fromEntity(LazadaProductDetailDto lazadaProductDetailDto, PublisherProductMappingEntity publisherProductMappingEntity) {
        PublisherProductDetailEntity publisherProductDetailEntity = new PublisherProductDetailEntity();
        publisherProductDetailEntity.setPublisherProductMappingEntity(publisherProductMappingEntity);
        publisherProductDetailEntity.setPublisherProductId(lazadaProductDetailDto.getData().getItemId());
        publisherProductDetailEntity.setPublisherProductName(PublisherName.LAZADA);
        publisherProductDetailEntity.setPrice(lazadaProductDetailDto.getData().getPrice());
        publisherProductDetailEntity.setDiscountRate(lazadaProductDetailDto.getData().getDiscountRate());
        publisherProductDetailEntity.setImgURL(lazadaProductDetailDto.getData().getImages().get(0));
        return publisherProductDetailEntity;
    }
}
