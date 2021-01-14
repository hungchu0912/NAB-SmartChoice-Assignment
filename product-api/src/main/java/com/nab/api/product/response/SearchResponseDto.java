package com.nab.api.product.response;

import com.nab.api.product.entity.PublisherProductMappingEntity;
import lombok.Data;

@Data
public class SearchResponseDto {
    private Integer id;
    private String productName;
    private Integer publisherProductId;

    public static SearchResponseDto fromEntity(PublisherProductMappingEntity publisherProductMappingEntity) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        searchResponseDto.setId(publisherProductMappingEntity.getId());
        searchResponseDto.setProductName(publisherProductMappingEntity.getProductName());
        searchResponseDto.setPublisherProductId(publisherProductMappingEntity.getPublisherEntity().getId());
        return searchResponseDto;
    }
}
