package com.nab.api.product.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publisher_product_mapping")
@Data
public class PublisherProductMappingEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "publisher_product_id")
    private String publisherProductId;
    @Column(name = "product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PublisherEntity publisherEntity;
    @OneToMany(mappedBy = "publisherProductMappingEntity", cascade = CascadeType.ALL)
    private List<PublisherProductDetailEntity> publisherProductDetailEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherProductMappingEntity that = (PublisherProductMappingEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(productName, that.productName) && Objects.equals(publisherProductId, that.publisherProductId) && Objects.equals(publisherEntity, that.publisherEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publisherProductId, publisherEntity, productName);
    }
}
