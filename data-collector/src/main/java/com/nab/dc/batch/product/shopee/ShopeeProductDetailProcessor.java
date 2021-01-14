package com.nab.dc.batch.product.shopee;

import com.nab.dc.batch.product.ProductRESTApiProcessor;
import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.repository.PublisherProductMappingRepository;
import com.nab.dc.repository.PublisherRepository;

public class ShopeeProductDetailProcessor extends ProductRESTApiProcessor<ShopeeProductDetailDto, PublisherProductDetailEntity> {

    @Override
    public PublisherProductDetailEntity fromEntity(ShopeeProductDetailDto shopeeProductDetailDto, PublisherProductMappingEntity publisherProductMappingEntity) {
        PublisherProductDetailEntity publisherProductDetailEntity = new PublisherProductDetailEntity();
        publisherProductDetailEntity.setPublisherProductId(shopeeProductDetailDto.getItem().getItemId());
        publisherProductDetailEntity.setPublisherProductMappingEntity(publisherProductMappingEntity);
        publisherProductDetailEntity.setPublisherProductName(PublisherName.SHOPEE);
        publisherProductDetailEntity.setPrice(shopeeProductDetailDto.getItem().getPrice());
        publisherProductDetailEntity.setDiscountRate(shopeeProductDetailDto.getItem().getRawDiscount());
        publisherProductDetailEntity.setImgURL(publisherProductDetailEntity.getImgURL());
        return publisherProductDetailEntity;
    }
}
