package com.nab.api.product.response;

import com.nab.api.product.entity.PublisherProductDetailEntity;
import lombok.Data;

@Data
public class ProductDetailResponseDto {
    private Integer id;
    private Double price;
    private String publisherProductId;
    private String publisherProductName;
    private Double discountRate;
    private String promotion;
    private String imgURL;

    public static ProductDetailResponseDto fromEntity(PublisherProductDetailEntity publisherProductDetailEntity) {
        ProductDetailResponseDto responseDto = new ProductDetailResponseDto();
        responseDto.setId(publisherProductDetailEntity.getId());
        responseDto.setPrice(publisherProductDetailEntity.getPrice());
        responseDto.setPublisherProductId(publisherProductDetailEntity.getPublisherProductId());
        responseDto.setPublisherProductName(publisherProductDetailEntity.getPublisherProductName());
        responseDto.setDiscountRate(publisherProductDetailEntity.getDiscountRate());
        responseDto.setPromotion(publisherProductDetailEntity.getPromotion());
        responseDto.setImgURL(publisherProductDetailEntity.getImgURL());
        return responseDto;
    }
}
