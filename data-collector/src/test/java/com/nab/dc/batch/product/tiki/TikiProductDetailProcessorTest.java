package com.nab.dc.batch.product.tiki;

import com.nab.dc.dto.tiki.TikiProductDetailDto;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TikiProductDetailProcessorTest {

    private static final String PRODUCT_ID = "tiki_1";
    private static final String PRODUCT_NAME = "dummy tiki name";

    @Test
    public void fromEntity_Success() {
        TikiProductDetailDto tikiProductDetailDto = new TikiProductDetailDto();
        tikiProductDetailDto.setProductId(PRODUCT_ID);
        tikiProductDetailDto.setName(PRODUCT_NAME);
        PublisherProductMappingEntity publisherProductMappingEntity = new PublisherProductMappingEntity();
        TikiProductDetailProcessor tikiProductDetailProcessor = new TikiProductDetailProcessor();
        PublisherProductDetailEntity publisherProductDetailEntity =
                tikiProductDetailProcessor.fromEntity(tikiProductDetailDto, publisherProductMappingEntity);
        assertEquals(PRODUCT_ID, publisherProductDetailEntity.getPublisherProductId());
        assertEquals(PRODUCT_NAME, publisherProductDetailEntity.getPublisherProductName());
    }
}
