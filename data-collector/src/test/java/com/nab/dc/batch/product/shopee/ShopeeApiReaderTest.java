package com.nab.dc.batch.product.shopee;

import com.nab.dc.dto.shopee.ShopeeProductDetailDto;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ShopeeApiReaderTest {

    @Mock
    PublisherProductMappingRepository publisherProductMappingRepository;

    @Mock
    Fetcher<String, ShopeeProductDetailDto> fetcher;

    ShopeeApiReader shopeeApiReader = new ShopeeApiReader(publisherProductMappingRepository, fetcher);

    @Test
    public void testPublisherName() {
        assertEquals(PublisherName.SHOPEE, shopeeApiReader.getPublisherName());
    }
}
