package com.nab.dc.batch.product.tiki;

import com.nab.dc.dto.tiki.TikiProductDetailDto;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TikiApiReaderTest {

    @Mock
    PublisherProductMappingRepository publisherProductMappingRepository;

    @Mock
    Fetcher<String, TikiProductDetailDto> fetcher;

    TikiApiReader tikiApiReader = new TikiApiReader(publisherProductMappingRepository, fetcher);

    @Test
    public void testPublisherName() {
        assertEquals(PublisherName.TIKI, tikiApiReader.getPublisherName());
    }
}
