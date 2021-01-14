package com.nab.api.product.service;

import com.nab.api.product.entity.PublisherProductDetailEntity;
import com.nab.api.product.entity.PublisherProductMappingEntity;
import com.nab.api.product.exception.NotFoundException;
import com.nab.api.product.exception.ValidationException;
import com.nab.api.product.repository.PublisherProductMappingRepository;
import com.nab.api.product.response.ProductDetailResponseDto;
import com.nab.api.product.response.ResponseWithPaging;
import com.nab.api.product.response.SearchResponseDto;
import com.nab.api.product.util.PageableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    PublisherProductMappingRepository publisherProductMappingRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    public static final String PRODUCT_NOT_FOUND        = "Product Id not found";
    public static final String PRODUCT_DETAIL_NOT_FOUND = "Product Detail not found";

    public ResponseWithPaging<SearchResponseDto> search(String name, Integer pageIndex, Integer pageSize) {
        Page<PublisherProductMappingEntity> resultPage = searchPublisherProductMappingEntities(name, pageIndex, pageSize);
        List<SearchResponseDto> result = resultPage
                .getContent()
                .stream()
                .map(SearchResponseDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseWithPaging.of(result).withFooter(resultPage);
    }

    public ProductDetailResponseDto detail(Integer productId) {
        PublisherProductMappingEntity publisherProductMappingEntity =
                publisherProductMappingRepository.findById(productId).orElseThrow(() -> new ValidationException(PRODUCT_NOT_FOUND));
        PublisherProductDetailEntity latestProductDetail = publisherProductMappingEntity.getPublisherProductDetailEntities()
                .stream().max(Comparator.comparing(PublisherProductDetailEntity::getUpdatedTime))
                .orElseThrow(() -> new NotFoundException(PRODUCT_DETAIL_NOT_FOUND));
        return ProductDetailResponseDto.fromEntity(latestProductDetail);
    }

    private Page<PublisherProductMappingEntity> searchPublisherProductMappingEntities(String name, Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageableUtil.getPageable(pageIndex, pageSize);
        return publisherProductMappingRepository.findAllByProductNameContains(name, pageable);
    }
}
