package com.nab.dc.batch.product.lazada;

import com.nab.dc.dto.lazada.LazadaProductDetailDto;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class LazadaApiReaderTest {

    @Mock
    PublisherProductMappingRepository publisherProductMappingRepository;

    @Mock
    Fetcher<String, LazadaProductDetailDto> fetcher;

    LazadaApiReader lazadaApiReader = new LazadaApiReader(publisherProductMappingRepository, fetcher);

    @Test
    public void testPublisherName() {
        assertEquals(PublisherName.LAZADA, lazadaApiReader.getPublisherName());
    }
}
