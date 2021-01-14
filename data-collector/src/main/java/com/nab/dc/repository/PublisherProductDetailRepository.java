package com.nab.dc.repository;

import com.nab.dc.entity.PublisherProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherProductDetailRepository extends JpaRepository<PublisherProductDetailEntity, Long> {
    List<PublisherProductDetailEntity> findAllByPublisherProductName(String name);
}
