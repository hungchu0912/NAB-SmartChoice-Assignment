package com.nab.dc.repository;

import com.nab.dc.entity.PublisherEntity;
import com.nab.dc.entity.constant.PublisherName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    PublisherEntity findByName(PublisherName name);
}
