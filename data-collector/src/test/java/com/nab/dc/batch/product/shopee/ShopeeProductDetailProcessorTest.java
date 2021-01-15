package com.nab.dc.batch.product.shopee;

import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ShopeeProductDetailProcessorTest {

    private static final String PRODUCT_ID = "shopee_1";
    private static final String PRODUCT_NAME = "dummy shopee name";

    @Test
    public void fromEntity_Success() {
        ShopeeProductDetailDto shopeeProductDetailDto = new ShopeeProductDetailDto();
        ShopeeProductDetailDto.Item item = new ShopeeProductDetailDto.Item();
        item.setItemId(PRODUCT_ID);
        item.setName(PRODUCT_NAME);
        shopeeProductDetailDto.setItem(item);
        PublisherProductMappingEntity publisherProductMappingEntity = new PublisherProductMappingEntity();
        ShopeeProductDetailProcessor shopeeProductDetailProcessor = new ShopeeProductDetailProcessor();
        PublisherProductDetailEntity publisherProductDetailEntity =
                shopeeProductDetailProcessor.fromEntity(shopeeProductDetailDto, publisherProductMappingEntity);
        assertEquals(PRODUCT_ID, publisherProductDetailEntity.getPublisherProductId());
        assertEquals(PRODUCT_NAME, publisherProductDetailEntity.getPublisherProductName());
    }
}
