package com.nab.api.product.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ErrorResponse {
    private int status;
    private OffsetDateTime timestamp;
    private String errorMessage;
}
