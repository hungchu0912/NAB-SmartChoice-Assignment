package com.nab.api.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "publisher_product_detail")
@Data
public class PublisherProductDetailEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price")
    private Double price;
    @Column(name = "publisher_product_id")
    private String publisherProductId;
    @Column(name = "publisher_product_name")
    private String publisherProductName;
    @Column(name = "discount_rate")
    private Double discountRate;
    @Column(name = "promotion")
    private String promotion;
    @Column(name = "img_url")
    private String imgURL;
    @Column(name = "updated_time")
    private Date updatedTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_product_mapping_id", referencedColumnName = "id")
    private PublisherProductMappingEntity publisherProductMappingEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherProductDetailEntity that = (PublisherProductDetailEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(discountRate, that.discountRate) && Objects.equals(promotion, that.promotion) &&  Objects.equals(publisherProductMappingEntity, that.publisherProductMappingEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, discountRate, promotion, publisherProductMappingEntity);
    }

    @PrePersist
    public void updateTime() {
        this.updatedTime = new Date();
    }
}
