package com.nab.dc.batch.product;

import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductRESTApiReaderTest {

    @Mock
    PublisherProductMappingRepository publisherProductMappingRepository;

    @Mock
    Fetcher fetcher;

    @Test
    public void read_Success() {
        ProductRESTApiReader productRESTApiReader = new MockProductRESTApiReader<>(publisherProductMappingRepository, fetcher);

    }

    private static class MockProductRESTApiReader<T> extends ProductRESTApiReader<T> {
        protected MockProductRESTApiReader(PublisherProductMappingRepository publisherProductMappingRepository, Fetcher<String, T> apiFetcher) {
            super(publisherProductMappingRepository, apiFetcher);
        }
        @Override
        protected PublisherName getPublisherName() {
            return PublisherName.TIKI;
        }
    }

}
