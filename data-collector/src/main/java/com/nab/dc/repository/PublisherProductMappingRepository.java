package com.nab.dc.repository;

import com.nab.dc.entity.PublisherProductMappingEntity;
import com.nab.dc.entity.constant.PublisherName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherProductMappingRepository extends JpaRepository<PublisherProductMappingEntity, Long> {
    @Query("SELECT p from PublisherProductMappingEntity p where p.publisherEntity.name=:name")
    List<PublisherProductMappingEntity> findAllByPublisherProductName(PublisherName name);
    Optional<PublisherProductMappingEntity> findByPublisherProductId(String publisherProductId);
}
