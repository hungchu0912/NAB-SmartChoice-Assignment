package com.nab.api.product.repository;

import com.nab.api.product.entity.PublisherProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherProductDetailRepository extends JpaRepository<PublisherProductDetailEntity, Long> {
    List<PublisherProductDetailEntity> findAllByPublisherProductName(String name);
}
