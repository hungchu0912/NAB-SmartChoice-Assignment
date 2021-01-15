package com.nab.dc.batch.product.shopee;

import com.nab.dc.batch.product.ProductRESTApiProcessor;
import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;

public class ShopeeProductDetailProcessor extends ProductRESTApiProcessor<ShopeeProductDetailDto, PublisherProductDetailEntity> {

    @Override
    public PublisherProductDetailEntity fromEntity(ShopeeProductDetailDto shopeeProductDetailDto, PublisherProductMappingEntity publisherProductMappingEntity) {
        PublisherProductDetailEntity publisherProductDetailEntity = new PublisherProductDetailEntity();
        publisherProductDetailEntity.setPublisherProductId(shopeeProductDetailDto.getItem().getItemId());
        publisherProductDetailEntity.setPublisherProductMappingEntity(publisherProductMappingEntity);
        publisherProductDetailEntity.setPublisherProductName(shopeeProductDetailDto.getItem().getName());
        publisherProductDetailEntity.setPrice(shopeeProductDetailDto.getItem().getPrice());
        publisherProductDetailEntity.setDiscountRate(shopeeProductDetailDto.getItem().getRawDiscount());
        publisherProductDetailEntity.setImgURL(publisherProductDetailEntity.getImgURL());
        return publisherProductDetailEntity;
    }
}
