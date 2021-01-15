package com.nab.dc.batch.product;

import com.nab.dc.entity.PublisherEntity;
import com.nab.dc.entity.PublisherProductDetailEntity;
import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.entity.constant.PublisherName;
import com.nab.dc.fetcher.Fetcher;
import com.nab.dc.repository.PublisherProductMappingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ProductRESTApiReaderTest {

    private static final String PRODUCT_NAME_1              = "dummy_product_name_1";
    private static final String PRODUCT_NAME_2              = "dummy_product_name_2";
    private static final String PRODUCT_ID_1                = "dummy_product_id_1";
    private static final String PRODUCT_ID_2                = "dummy_product_id_2";
    private static final PublisherName DUMMY_PUBLISHER_NAME = PublisherName.TIKI;

    @Mock
    PublisherProductMappingRepository publisherProductMappingRepository;

    @Mock
    Fetcher<String, Object> fetcher;

    @InjectMocks
    ProductRESTApiReader<Object> productRESTApiReader = new ProductRESTApiReader<Object>(publisherProductMappingRepository, fetcher) {
        @Override
        protected PublisherName getPublisherName() {
            return DUMMY_PUBLISHER_NAME;
        }
    };

    @Test
    public void read_Success() {
        List<PublisherProductMappingEntity> dummyPublisherProductMappingEntityList = constructListProductMapping();
        Object dummyResponseFromPublisher1 = new Object();
        Object dummyResponseFromPublisher2 = new Object();
        Mockito.when(publisherProductMappingRepository.findAllByPublisherProductName(DUMMY_PUBLISHER_NAME)).thenReturn(dummyPublisherProductMappingEntityList);
        Mockito.when(fetcher.fetch(PRODUCT_ID_1)).thenReturn(dummyResponseFromPublisher1);
        Mockito.when(fetcher.fetch(PRODUCT_ID_2)).thenReturn(dummyResponseFromPublisher2);
        productRESTApiReader.read();
        assertEquals(productRESTApiReader.detailDtoMap.get(dummyPublisherProductMappingEntityList.get(0)), dummyResponseFromPublisher1);
        assertEquals(productRESTApiReader.detailDtoMap.get(dummyPublisherProductMappingEntityList.get(1)), dummyResponseFromPublisher2);
    }

    @Test
    public void read_Empty() {
        Mockito.when(publisherProductMappingRepository.findAllByPublisherProductName(DUMMY_PUBLISHER_NAME)).thenReturn(new ArrayList<>());
        productRESTApiReader.read();
        assertEquals(productRESTApiReader.detailDtoMap.size(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void read_RuntimeExceptionWhenFetch() {
        List<PublisherProductMappingEntity> dummyPublisherProductMappingEntityList = constructListProductMapping();
        Mockito.when(publisherProductMappingRepository.findAllByPublisherProductName(DUMMY_PUBLISHER_NAME)).thenReturn(dummyPublisherProductMappingEntityList);
        Mockito.when(fetcher.fetch(PRODUCT_ID_1)).thenThrow(new RuntimeException());
        productRESTApiReader.read();
    }

    private List<PublisherProductMappingEntity> constructListProductMapping() {
        List<PublisherProductMappingEntity> dummyPublisherProductMappingEntityList = new ArrayList<>();
        dummyPublisherProductMappingEntityList.add(constructProductMapping(1, PRODUCT_NAME_1, PRODUCT_ID_1));
        dummyPublisherProductMappingEntityList.add(constructProductMapping(2, PRODUCT_NAME_2, PRODUCT_ID_2));
        return dummyPublisherProductMappingEntityList;
    }

    private PublisherProductMappingEntity constructProductMapping(Integer id, String name, String productId) {
        PublisherProductMappingEntity dummyPublisherProductMapping = new PublisherProductMappingEntity();
        PublisherEntity dummypublisherEntity = new PublisherEntity();
        dummypublisherEntity.setId(1);
        dummyPublisherProductMapping.setId(id);
        dummyPublisherProductMapping.setProductName(name);
        dummyPublisherProductMapping.setPublisherProductId(productId);
        dummyPublisherProductMapping.setPublisherEntity(dummypublisherEntity);
        return dummyPublisherProductMapping;
    }

    private List<PublisherProductDetailEntity> constructListProductDetail() {
        List<PublisherProductDetailEntity> dummyPublisherProductDetailList = new ArrayList<>();
        dummyPublisherProductDetailList.add(constructProductDetail(1, PRODUCT_NAME_1, 1000D, 20D, new Date(1000000)));
        dummyPublisherProductDetailList.add(constructProductDetail(2, PRODUCT_NAME_2, 2000D, 25D, new Date(2000000)));
        return dummyPublisherProductDetailList;
    }

    private PublisherProductDetailEntity  constructProductDetail(Integer id, String name, Double price, Double discountRate, Date updatedTime) {
        PublisherProductDetailEntity dummyPublisherProductDetail = new PublisherProductDetailEntity();
        dummyPublisherProductDetail.setId(id);
        dummyPublisherProductDetail.setPrice(price);
        dummyPublisherProductDetail.setDiscountRate(discountRate);
        dummyPublisherProductDetail.setUpdatedTime(updatedTime);
        return dummyPublisherProductDetail;
    }
}
