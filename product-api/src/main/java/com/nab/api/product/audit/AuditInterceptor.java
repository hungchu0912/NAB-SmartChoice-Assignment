package com.nab.api.product.audit;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuditInterceptor implements HandlerInterceptor {

    public static final String USER_ID_HEADER = "x-authenticated-user-id";

    private String auditTopic;
    private final JmsTemplate jmsTemplate;

    public AuditInterceptor(JmsTemplate jmsTemplate, String auditTopic) {
        this.jmsTemplate = jmsTemplate;
        this.auditTopic = auditTopic;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        AuditMessageDto auditMessageDto = new AuditMessageDto();
        auditMessageDto.setUserId(request.getHeader(USER_ID_HEADER));
        auditMessageDto.setMethod(request.getMethod());
        auditMessageDto.setUri(request.getRequestURI());
        auditMessageDto.setParams(request.getParameterMap());
        jmsTemplate.convertAndSend(auditTopic, auditMessageDto);
        return true;
    }
}
