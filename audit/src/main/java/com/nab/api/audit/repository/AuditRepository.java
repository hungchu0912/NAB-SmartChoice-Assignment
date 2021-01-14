package com.nab.api.audit.repository;

import com.nab.api.audit.model.AuditMessageDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditMessageDto, String> {
}
