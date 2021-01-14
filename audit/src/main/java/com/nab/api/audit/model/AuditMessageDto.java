package com.nab.api.audit.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;

@Data
public class AuditMessageDto implements Serializable {
    @Id
    private String id;
    private String userId;
    private String method;
    private String uri;
    private Map<String, String[]> params;
}
