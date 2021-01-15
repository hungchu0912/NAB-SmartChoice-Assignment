package com.nab.api.product.service;

import com.nab.api.product.entity.PublisherEntity;
import com.nab.api.product.entity.PublisherProductDetailEntity;
import com.nab.api.product.entity.PublisherProductMappingEntity;
import com.nab.api.product.exception.ValidationException;
import com.nab.api.product.repository.PublisherProductMappingRepository;
import com.nab.api.product.response.ProductDetailResponseDto;
import com.nab.api.product.response.ResponseWithPaging;
import com.nab.api.product.response.SearchResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    PublisherProductMappingRepository publisherProductMappingRepository;

    private static final String SEARCH_PARAM   = "dummy_search_param";
    private static final String PRODUCT_NAME_1 = "dummy_product_name_1";
    private static final String PRODUCT_NAME_2 = "dummy_product_name_2";
    private static final String PRODUCT_ID_1   = "dummy_product_id_1";
    private static final String PRODUCT_ID_2   = "dummy_product_id_2";

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void search_Success() {
        List<PublisherProductMappingEntity> dummyPublisherProductMappingEntityList = constructListProductMapping();
        ResponseWithPaging<SearchResponseDto> response = setupSearch(dummyPublisherProductMappingEntityList);
        assertEquals(2 ,response.getData().size());
        assertEquals(new Integer(1), response.getData().get(0).getId());
        assertEquals(new Integer(2), response.getData().get(1).getId());
    }

    @Test
    public void search_Empty() {
        List<PublisherProductMappingEntity> dummyPublisherProductMappingEntityList = new ArrayList<>();
        ResponseWithPaging<SearchResponseDto> response = setupSearch(dummyPublisherProductMappingEntityList);
        assertEquals(0, response.getData().size());
    }

    @Test
    public void detail_Success() {
        PublisherProductMappingEntity publisherProductMappingEntity = constructProductMapping(1, PRODUCT_NAME_1, PRODUCT_ID_1);
        publisherProductMappingEntity.setPublisherProductDetailEntities(constructListProductDetail());
        Mockito.when(publisherProductMappingRepository.findById(eq(1)))
                .thenReturn(Optional.of(publisherProductMappingEntity));
        ProductDetailResponseDto responseDto = productService.detail(1);
        assertEquals(responseDto.getId(), new Integer(2));
        assertEquals(responseDto.getPrice(), new Double(2000));
    }

    @Test(expected = ValidationException.class)
    public void detail_ProductNotFound() {
        Mockito.when(publisherProductMappingRepository.findById(eq(1)))
                .thenReturn(Optional.empty());
        ProductDetailResponseDto responseDto = productService.detail(1);
    }

    private ResponseWithPaging<SearchResponseDto> setupSearch(List<PublisherProductMappingEntity> dummyPublisherProductMappingEntityList) {
        Page<PublisherProductMappingEntity> pageMock = Mockito.mock(Page.class);
        Mockito.when(pageMock.getContent()).thenReturn(dummyPublisherProductMappingEntityList);
        Mockito.when(publisherProductMappingRepository.findAllByProductNameContains(eq(SEARCH_PARAM), Mockito.any()))
                .thenReturn(pageMock);
        ResponseWithPaging<SearchResponseDto> response = productService.search(SEARCH_PARAM, 0, 0);
        return response;
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

    private PublisherProductDetailEntity constructProductDetail(Integer id, String name, Double price, Double discountRate, Date updatedTime) {
        PublisherProductDetailEntity dummyPublisherProductDetail = new PublisherProductDetailEntity();
        dummyPublisherProductDetail.setId(id);
        dummyPublisherProductDetail.setPublisherProductName(name);
        dummyPublisherProductDetail.setPrice(price);
        dummyPublisherProductDetail.setDiscountRate(discountRate);
        dummyPublisherProductDetail.setUpdatedTime(updatedTime);
        return dummyPublisherProductDetail;
    }
}
