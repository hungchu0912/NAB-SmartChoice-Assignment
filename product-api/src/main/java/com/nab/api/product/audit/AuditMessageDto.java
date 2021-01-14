package com.nab.api.product.audit;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class AuditMessageDto implements Serializable {
    private String userId;
    private String method;
    private String uri;
    private Map<String, String[]> params;
}
