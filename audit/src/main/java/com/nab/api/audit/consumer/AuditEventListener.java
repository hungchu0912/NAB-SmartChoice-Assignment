package com.nab.api.audit.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.api.audit.model.AuditMessageDto;
import com.nab.api.audit.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class AuditEventListener {

    @Autowired
    AuditRepository auditRepository;

    @Autowired
    ObjectMapper objectMapper;

    @JmsListener(destination = "product_audit")
    public void receiveMessage(TextMessage textMessage) throws JMSException, JsonProcessingException {
        AuditMessageDto auditMessageDto = objectMapper.readValue(textMessage.getText(), AuditMessageDto.class);
        auditRepository.save(auditMessageDto);
    }
}
