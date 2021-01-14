package com.nab.api.product.repository;

import com.nab.api.product.entity.PublisherEntity;
import com.nab.api.product.entity.constant.PublisherName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    PublisherEntity findByName(PublisherName name);
}
