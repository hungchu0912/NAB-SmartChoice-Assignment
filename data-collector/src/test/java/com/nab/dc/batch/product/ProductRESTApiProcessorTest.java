package com.nab.dc.batch.product;

import com.nab.dc.entity.PublisherProductMappingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ProductRESTApiProcessorTest {

    DummyInput dummyInput = new DummyInput();
    DummyOutput dummyOutput = new DummyOutput();

    ProductRESTApiProcessor<DummyInput, DummyOutput> productRESTApiProcessor = new ProductRESTApiProcessor<DummyInput, DummyOutput>() {
        @Override
        public DummyOutput fromEntity(DummyInput productDetailDto, PublisherProductMappingEntity publisherProductMappingEntity) {
            return dummyOutput;
        }
    };

    @Test
    public void process_success() {
        Map<PublisherProductMappingEntity, DummyInput> productDetailMap = new HashMap<>();
        PublisherProductMappingEntity dummyPublisherProductMappingEntity = new PublisherProductMappingEntity();
        productDetailMap.put(dummyPublisherProductMappingEntity, dummyInput);
        List<DummyOutput> dummyOutputList = productRESTApiProcessor.process(productDetailMap);
        assertEquals(1, dummyOutputList.size());
        assertEquals(dummyOutput, dummyOutputList.get(0));
    }

    @Test
    public void process_empty() {
        Map<PublisherProductMappingEntity, DummyInput> productDetailMap = new HashMap<>();
        PublisherProductMappingEntity dummyPublisherProductMappingEntity = new PublisherProductMappingEntity();
        List<DummyOutput> dummyOutputList = productRESTApiProcessor.process(productDetailMap);
        assertEquals(0, dummyOutputList.size());
    }

    private static class DummyInput {}
    private static class DummyOutput {}
}
