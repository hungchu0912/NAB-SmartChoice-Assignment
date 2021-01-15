package com.nab.dc.batch.product.lazada;

import com.nab.dc.dto.lazada.LazadaProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class LazadaProductDetailProcessorTest {

    private static final String PRODUCT_ID = "lazada_1";
    private static final String PRODUCT_NAME = "dummy lazada name";

    @Test
    public void fromEntity_Success() {
        LazadaProductDetailDto lazadaProductDetailDto = new LazadaProductDetailDto();
        LazadaProductDetailDto.DataDto dataDto = new LazadaProductDetailDto.DataDto();
        dataDto.setName(PRODUCT_NAME);
        dataDto.setItemId(PRODUCT_ID);
        lazadaProductDetailDto.setData(dataDto);
        PublisherProductMappingEntity publisherProductMappingEntity = new PublisherProductMappingEntity();
        LazadaProductDetailProcessor lazadaProductDetailProcessor = new LazadaProductDetailProcessor();
        PublisherProductDetailEntity publisherProductDetailEntity =
                lazadaProductDetailProcessor.fromEntity(lazadaProductDetailDto, publisherProductMappingEntity);
        assertEquals(PRODUCT_ID, publisherProductDetailEntity.getPublisherProductId());
        assertEquals(PRODUCT_NAME, publisherProductDetailEntity.getPublisherProductName());
    }
}
