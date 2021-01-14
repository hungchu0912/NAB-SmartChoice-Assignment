package com.nab.api.product.repository;

import com.nab.api.product.entity.PublisherProductMappingEntity;
import com.nab.api.product.entity.constant.PublisherName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherProductMappingRepository extends JpaRepository<PublisherProductMappingEntity, Integer> {
    @Query("SELECT p from PublisherProductMappingEntity p where p.publisherEntity.name=:name")
    List<PublisherProductMappingEntity> findAllByPublisherProductName(PublisherName name);

    Page<PublisherProductMappingEntity> findAllByProductNameContains(String name, Pageable pageable);
}
